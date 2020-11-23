version_name=$1

echo "Tagging release with tag $version_name"
commit_message=$(git log -1 --pretty=%B)

echo "commit message $commit_message"
git config user.name ${GITHUB_ACTOR}
git config user.email ${GITHUB_ACTOR}@zomato.com

git tag -a $version_name -m ""
git push origin --tags

echo "VERSION_NAME=$version_name" >> $GITHUB_ENV
echo 'COMMIT_MESSAGE<<EOF' >> $GITHUB_ENV
echo "$commit_message" >> $GITHUB_ENV
echo 'EOF' >> $GITHUB_ENV