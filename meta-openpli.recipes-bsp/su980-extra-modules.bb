DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

KV = '2.6.34'
PV = '20131126'

PR = "${GMVERSION}.${GMREVISION}"

S = "${WORKDIR}"

SRC_URI = "file://${MACHINE}-extra-modules-${PV}.tar.gz"

FILES_${PN} += " \
	${base_libdir}/* \
	${sysconfdir}/* \
	"

do_compile() {
}

do_install() {
	install -d ${D}/lib/modules/${KV}/extra/
	for f in *.ko; do
		install -m 0644 ${WORKDIR}/$f ${D}/lib/modules/${KV}/extra/$f;
	done

	install -d ${D}/${sysconfdir}/modules-load.d
	for i in `ls | grep \\.ko | sed -e 's/.ko//g'`; do
		echo $i >> ${D}/${sysconfdir}/modules-load.d/extra.conf
	done
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
