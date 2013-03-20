RUN_HORNET="export CLUSTER_PROPS=\"-Djnp.port=1099 -Djnp.rmiPort=1098 -Djnp.host=localhost -Dhornetq.remoting.netty.host=localhost -Dhornetq.remoting.netty.port=5445\"
HORNETQ_DIR=\`dirname \"\${0}\"\`
echo \"\${HORNETQ_DIR}/bin\"
pushd \"\${HORNETQ_DIR}/bin\"
./run.sh
popd
"
MAIL_QUEUE="    <queue name=\"mailSenderQueue\">\n		<entry name=\"\/queue\/mailSenderQueue\"\/>\n	<\/queue>"


HORNET_NAME="hornetq-2.2.14.Final"
HORNET_RUN_SCRIPT="${HORNET_NAME}/runHornetQ.sh"
wget "http://downloads.jboss.org/hornetq/${HORNET_NAME}.zip"
unzip "${HORNET_NAME}.zip"

echo "${RUN_HORNET}" > ${HORNET_RUN_SCRIPT}
chmod +x ${HORNET_RUN_SCRIPT}

HORNET_JMS_CONFIGURATION="${HORNET_NAME}/config/stand-alone/non-clustered/hornetq-jms.xml"
END_OF_CONFIGURATION="<\/configuration>"
HORNET_JMS_CONF_TEMP_FILE="/tmp/hornetq-jms-`date`.xml"
sed "s/${END_OF_CONFIGURATION}/${MAIL_QUEUE}\n&/g" ${HORNET_JMS_CONFIGURATION} > "${HORNET_JMS_CONF_TEMP_FILE}"
mv "${HORNET_JMS_CONF_TEMP_FILE}" "${HORNET_JMS_CONFIGURATION}"
