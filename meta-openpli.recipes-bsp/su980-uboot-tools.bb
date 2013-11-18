DESCRIPTION = "uboot tools for ${MACHINE}"
SECTION = "base"
LICENSE = "CLOSED"

PV = '20120615'
PR = "${GMREVISION}"

SRC_URI = "http://images.hdmedia-universe.com/anderes/${MACHINE}-uboot-tools-${PV}.tar.gz"

FILES_${PN} = "${bindir}/*"

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/getenv ${D}/${bindir}
	install -m 0755 ${S}/setenv ${D}/${bindir}
}
