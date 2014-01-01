DESCRIPTION = "uboot tools for ${MACHINE}"
SECTION = "base"
LICENSE = "CLOSED"

PV = '20120615'

SRC_URI = "http://images.hdmedia-universe.com/anderes/${MACHINE}-uboot-tools-${PV}.tar.gz"

FILES_${PN} = "${bindir}/*"

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/getenv ${D}/${bindir}
	install -m 0755 ${S}/setenv ${D}/${bindir}
}

SRC_URI[md5sum] = "99acf0329620e86ef51376d5533f7b56"
SRC_URI[sha256sum] = "ccc4c72fedb165ddf8e2450f620000a3c8945399df36b9c01a90a860766ebea3"
