# This script merges the destination branch to the source branch with no edit & push
source_branch=$1
destination_branch=$2
git config user.name ${GITHUB_ACTOR}
git config user.email ${GITHUB_ACTOR}@zomato.com
git fetch
git checkout origin/$source_branch -f
git checkout $source_branch -f
git pull origin $source_branch
git pull origin $source_branch
git merge origin/$destination_branch --no-ff --no-edit --allow-unrelated-histories
git push origin $source_branch