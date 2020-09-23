#!/bin/sh

RUNAS="exec su-exec cocoapods"

gomplate -d event=${GITHUB_EVENT_PATH} -f BuzzAdBenefit.podspec.tmpl -o BuzzAdBenefit.podspec
$RUNAS sh<<_
  pod spec lint BuzzAdBenefit.podspec
  pod trunk push BuzzAdBenefit.podspec --allow-warnings
_
