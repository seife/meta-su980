DESCRIPTION = "ca test for ${MACHINE} factory test"
SECTION = "base"
LICENSE = "CLOSED"

PV = '20121023'
PR = "${GMREVISION}"

SRC_URI = "http://images.hdmedia-universe.com/anderes/${MACHINE}-ca-test-${PV}.tar.gz"

FILES_${PN} = "${bindir}/*"

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/ca_test ${D}/${bindir}
}
