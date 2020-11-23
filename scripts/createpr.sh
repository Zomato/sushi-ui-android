GITHUB_TOKEN=$1
INPUT_SOURCE_BRANCH=$2
INPUT_DESTINATION_BRANCH=$3
INPUT_PR_ALLOW_EMPTY=$4
INPUT_PR_TITLE=$5
INPUT_PR_BODY=$6
echo "params: $GITHUB_TOKEN $INPUT_SOURCE_BRANCH $INPUT_DESTINATION_BRANCH $INPUT_PR_ALLOW_EMPTY $INPUT_PR_TITLE $INPUT_PR_BODY"

set -e

if [[ -z "$GITHUB_TOKEN" ]]; then
  echo "Set the GITHUB_TOKEN environment variable."
  exit 1
fi

if [[ ! -z "$INPUT_SOURCE_BRANCH" ]]; then
  SOURCE_BRANCH="$INPUT_SOURCE_BRANCH"
elif [[ ! -z "$GITHUB_REF" ]]; then
  SOURCE_BRANCH=${GITHUB_REF/refs\/heads\//}  # Remove branch prefix
else
  echo "Set the INPUT_SOURCE_BRANCH environment variable or trigger from a branch."
  exit 1
fi

echo "Source branch $SOURCE_BRANCH"

DESTINATION_BRANCH="${INPUT_DESTINATION_BRANCH:-"master"}"

echo "Destination branch $DESTINATION_BRANCH"

# Github actions no longer auto set the username and GITHUB_TOKEN
git config user.name ${GITHUB_ACTOR}
git config user.email ${GITHUB_ACTOR}@zomato.com

echo "repo link $GITHUB_REPOSITORY"
echo "actor $GITHUB_ACTOR"
echo "token $GITHUB_TOKEN"

git remote set-url origin "https://$GITHUB_ACTOR:$GITHUB_TOKEN@github.com/$GITHUB_REPOSITORY"

echo "listing all remotes"
git remote -v

# Pull all branches references down locally so subsequent commands can see them
git fetch origin '+refs/heads/*:refs/heads/*' --update-head-ok

# Print out all branches
git --no-pager branch -a -vv

if [ "$(git rev-parse --revs-only "$SOURCE_BRANCH")" = "$(git rev-parse --revs-only "$DESTINATION_BRANCH")" ]; then 
  echo "Source and destination branches are the same." 
  exit 0
fi

# Do not proceed if there are no file differences, this avoids PRs with just a merge commit and no content
LINES_CHANGED=$(git diff --name-only "$DESTINATION_BRANCH" "$SOURCE_BRANCH" | wc -l | awk '{print $1}')
if [[ "$LINES_CHANGED" = "0" ]] && [[ ! "$INPUT_PR_ALLOW_EMPTY" ==  "true" ]]; then
  echo "No file changes detected between source and destination branches." 
  exit 0
fi

echo "lines changed $LINES_CHANGED"

# Workaround for `hub` auth error https://github.com/github/hub/issues/2149#issuecomment-513214342
export GITHUB_USER="$GITHUB_ACTOR"

PR_ARG="$INPUT_PR_TITLE"
if [[ ! -z "$PR_ARG" ]]; then
  PR_ARG="-m \"$PR_ARG\""

  if [[ ! -z "$INPUT_PR_BODY" ]]; then
    PR_ARG="$PR_ARG -m \"$INPUT_PR_BODY\""
  fi
fi

echo "current branch"
git rev-parse --abbrev-ref HEAD

COMMAND="hub pull-request \
  -b $DESTINATION_BRANCH \
  -h $SOURCE_BRANCH \
  $PR_ARG \
  --no-edit --push \
  || true"

echo "$COMMAND"

PR_URL=$(sh -c "$COMMAND")
if [[ "$?" != "0" ]]; then
  exit 1
fi

echo ${PR_URL}
echo "::set-output name=pr_url::${PR_URL}"