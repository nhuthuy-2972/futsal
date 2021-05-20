FROM airhacks/glassfish
COPY ./target/futsalmangement.war ${DEPLOYMENT_DIR}
