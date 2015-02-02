FILESEXTRAPATHS_prepend := "${THISDIR}/../../meta-openpli.recipes-bsp/su980-shutdown:"

require conf/license/license-gplv2.inc

SRC_URI = " \
	file://turnoff_power \
	file://su980-shutdown.sh "

INITSCRIPT_NAME = "su980-shutdown"
INITSCRIPT_PARAMS = "start 89 0 ."

inherit update-rc.d

do_install() {
	install -d ${D}/etc/init.d/
	install -m 0755 ${WORKDIR}/su980-shutdown.sh ${D}/etc/init.d/su980-shutdown
	install -d ${D}/usr/bin
	install -m 0755 ${WORKDIR}/turnoff_power ${D}/usr/bin
}

INSANE_SKIP_${PN} = "ldflags"
PACKAGE_ARCH = "${MACHINE_ARCH}"
