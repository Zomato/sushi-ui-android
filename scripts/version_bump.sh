VERSION_PROPERTIES_PATH=$1
DEFAULT_BRANCH="dev"
COMMIT_BRANCH="release"
VERSION_CODE_VAR="VERSION_CODE"
VERSION_NAME_VAR="VERSION_NAME"

git config user.name ${GITHUB_ACTOR}
git config user.email ${GITHUB_ACTOR}@zomato.com

# get the tag of last release
git fetch
git_latest_tag=$(git describe --tags `git rev-list --tags --max-count=1`)

echo "Latest tag: $git_latest_tag"

# get the tag for upcoming release
git_upcoming_tag="$(echo $git_latest_tag | awk -F. -v OFS=. 'NF==1{print ++$NF}; NF>1{if(length($NF+1)>length($NF))$(NF-1)++; $NF=sprintf("%0*d", length($NF), ($NF+1)%(10^length($NF))); print}')"
echo "Upcoming tag: $git_upcoming_tag"

#Remove 'v' character in tag
VERSION_NAME_VALUE=${git_upcoming_tag#"v"}

# Get version code from VERSION_NAME
base_version_code='1710000000'
version_code_addition="${VERSION_NAME_VALUE//./}"
VERSION_CODE_VALUE=`expr $base_version_code + $version_code_addition \* 10`

# Print version properties
echo "Printing version.properties"
echo "VERSION_CODE: $VERSION_CODE_VALUE"
echo "VERSION_NAME: $VERSION_NAME_VALUE"

#create the smoke testing branch with new version string
git checkout $DEFAULT_BRANCH
git checkout -b $COMMIT_BRANCH/$git_upcoming_tag
git reset --hard origin/$DEFAULT_BRANCH

# Update version.properties

# Write to file
cat > $VERSION_PROPERTIES_PATH <<EOF
$VERSION_CODE_VAR=$VERSION_CODE_VALUE
$VERSION_NAME_VAR=$VERSION_NAME_VALUE
EOF

echo "version name: $VERSION_NAME_VALUE"
echo "upcoming tag: $git_upcoming_tag"

# upgrade version
git add $VERSION_PROPERTIES_PATH
git commit -m "Going live | $VERSION_NAME_VALUE"

# Commit and push
git push origin $COMMIT_BRANCH/$git_upcoming_tag --force

echo $git_upcoming_tag