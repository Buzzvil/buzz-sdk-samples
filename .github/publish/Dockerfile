FROM ruby:2-stretch

LABEL version="1.0.0"
LABEL repository="http://github.com/Buzzvil/buzzad-benefit-sdk-publisher-ios"
LABEL maintainer="Buzzvil"
LABEL "com.github.actions.name"="Publish cocoapods"
LABEL "com.github.actions.description"="This is an action to publish cocoapods."
LABEL "com.github.actions.icon"="upload-cloud"
LABEL "com.github.actions.color"="green-dark"

RUN apt-get update && apt-get install -y jq && \
    gem install cocoapods -v 1.6.2
ADD entrypoint.sh /entrypoint.sh

ENTRYPOINT [ "/entrypoint.sh" ]
