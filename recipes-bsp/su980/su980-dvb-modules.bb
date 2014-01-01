FILESEXTRAPATHS_prepend := "${THISDIR}/../../meta-openpli.recipes-bsp/"

DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = '2.6.34'
PV = '20131127'

S = "${WORKDIR}"

inherit module update-rc.d

SRC_URI = "file://${MACHINE}-dvb-modules-${PV}.tar.gz"

FILES_${PN} += " \
	${base_libdir}/* \
	${sysconfdir}/* \
	"

INITSCRIPT_NAME = "populate-private-nodes.sh"
INITSCRIPT_PARAMS = "start 20 S ."

do_compile() {
}

do_install() {
	install -d ${D}/lib/modules/${KV}/extra/
	for f in *.ko; do
		install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KV}/extra/$f;
	done

	install -d ${D}/${base_libdir}/firmware
	install -m 644 ${WORKDIR}/firmware/* ${D}/${base_libdir}/firmware/

	install -d ${D}/${sysconfdir}/Wireless/RT2870STA
	install -m 644 ${WORKDIR}/RT2870STA.dat ${D}/${sysconfdir}/Wireless/RT2870STA

	install -d ${D}/${sysconfdir}/modules-load.d
	for i in `ls | grep \\.ko | sed -e 's/.ko//g'`; do
		echo $i >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
	done

	install -d ${D}/${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/${INITSCRIPT_NAME} ${D}/${sysconfdir}/init.d/

	install -d ${D}/${sysconfdir}/modprobe.d
	install -m 644 ${WORKDIR}/vpmfbDrv.conf ${D}/${sysconfdir}/modprobe.d
}
