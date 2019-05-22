#!/bin/sh

push() {
  gomplate -d event=${GITHUB_EVENT_PATH} -f BuzzAdBenefit.podspec.tmpl -o BuzzAdBenefit.podspec
  pod init
  cat BuzzAdBenefit.podspec
}

push
