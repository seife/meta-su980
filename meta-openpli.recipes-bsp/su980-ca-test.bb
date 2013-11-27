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


SRC_URI[md5sum] = "93670757e653df702454fae2ee776b23"
SRC_URI[sha256sum] = "0fb1c46653973a25838799354871f31c3f140241b34b1b6022eeda2feb3f3616"
