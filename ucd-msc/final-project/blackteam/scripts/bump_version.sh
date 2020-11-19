#!/bin/bash
source ../.env

#if !  git-flow-version > .git-flow-version.new; then
#  echo "Could not replace GITFLOW_VERSION variable." >&2
#  exit 2
#fi
commit_new_version(){
  git add ../.env
  git commit -m "Bumped version number to $1" ../.env
  git tag -a v$1 -m "Newsfast version $1"
}

version_tag(){
  #num=$(echo "x=0.1 + $NEWSFAST_VERSION; if(x<1) print 0; x" | bc)
  #echo $num
  revisioncount=`git log --oneline | wc -l | tr -d ' '`
  revisionid=`git log --pretty=format:'%h' -n 1`
  projectversion=`git describe --tags --long`
  cleanversion=${NEWSFAST_VERSION%%-*}
   
  new_version_num="$cleanversion-$revisioncount-$revisionid"
  printf "\n\nChanging version from:\n\t $NEWSFAST_VERSION\nto:\n\t $new_version_num\n\n"
  sed 's/^NEWSFAST_VERSION=.*$/NEWSFAST_VERSION='$new_version_num'/g' ../.env > ../.env-new 
  mv ../.env-new ../.env
  commit_new_version $new_version_num
}

version_tag
