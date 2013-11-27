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

SRC_URI[md5sum] = "33a10b93fc84839a04bf2d3f5a666ab5"
SRC_URI[sha256sum] = "840e9f2dca7b1d9f6cfaf7de13c4cfad87fff00a3ed5009ef0b25c3a3c1ce11e"
