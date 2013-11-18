DESCRIPTION = "User space libs for ${MACHINE}"
SECTION = "base"
LICENSE = "CLOSED"

PV = '20130221'
PR = "${GMREVISION}"

SRC_URI = "http://images.hdmedia-universe.com/anderes/${MACHINE}-user-libs-${PV}.tar.gz"

FILES_${PN} = "${libdir}/*"

do_install() {
	install -d ${D}/${libdir}
	for f in *.a  *.so; do
		install -m 0644 ${S}/$f ${D}/${libdir}
	done
}
