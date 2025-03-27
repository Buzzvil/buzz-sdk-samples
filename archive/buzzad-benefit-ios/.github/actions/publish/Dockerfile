FROM gcr.io/devops-229509/cocoapods:1.6.2

LABEL version="1.0.0"
LABEL repository="http://github.com/Buzzvil/buzzad-benefit-sdk-publisher-ios"
LABEL maintainer="Buzzvil"
LABEL "com.github.actions.name"="Publish cocoapods"
LABEL "com.github.actions.description"="This is an action to publish cocoapods."
LABEL "com.github.actions.icon"="upload-cloud"
LABEL "com.github.actions.color"="green-dark"

ADD entrypoint.sh /entrypoint.sh

ENTRYPOINT [ "/entrypoint.sh" ]
