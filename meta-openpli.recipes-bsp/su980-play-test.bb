DESCRIPTION = "play test for ${MACHINE} factory test"
SECTION = "base"
LICENSE = "CLOSED"

PV = '20130124'
PR = "${GMREVISION}"

SRC_URI = "http://images.hdmedia-universe.com/anderes/${MACHINE}-play-test-${PV}.tar.gz"

FILES_${PN} = "${bindir}/*"

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/play_test ${D}/${bindir}
}
