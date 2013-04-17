#!/bin/bash

RUN_HORNET="export CLUSTER_PROPS=\"-Djnp.port=1099 -Djnp.rmiPort=1098 -Djnp.host=localhost -Dhornetq.remoting.netty.host=localhost -Dhornetq.remoting.netty.port=5445\"
HORNETQ_DIR=\`dirname \"\${0}\"\`
echo \"\${HORNETQ_DIR}/bin\"
pushd \"\${HORNETQ_DIR}/bin\"
./run.sh
popd
"

HORNET_NAME="hornetq-2.2.14.Final"
HORNET_RUN_SCRIPT="${HORNET_NAME}/runHornetQ.sh"

if [[ $# > 0 && $1 == "noDownload" ]]
then
   echo "Skipping download"
else
   wget "http://downloads.jboss.org/hornetq/${HORNET_NAME}.zip"
fi

unzip "${HORNET_NAME}.zip"

echo "${RUN_HORNET}" > ${HORNET_RUN_SCRIPT}
chmod +x ${HORNET_RUN_SCRIPT}

HORNET_JMS_CONFIGURATION_DIR="${HORNET_NAME}/config/stand-alone/non-clustered/"
cp conf/* "${HORNET_JMS_CONFIGURATION_DIR}"


