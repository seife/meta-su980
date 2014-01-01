DESCRIPTION = "User space libs for ${MACHINE}"
SECTION = "base"
LICENSE = "CLOSED"

PV = '20130221'

SRC_URI = "http://images.hdmedia-universe.com/anderes/${MACHINE}-user-libs-${PV}.tar.gz"

FILES_${PN} = "${libdir}/*"

do_install() {
	install -d ${D}/${libdir}
	for f in *.a  *.so; do
		install -m 0644 ${S}/$f ${D}/${libdir}
	done
}

SRC_URI[md5sum] = "eedb4d3da3432f0fa45ee12ecb1eb919"
SRC_URI[sha256sum] = "3a70dd81f4e6520be7804a08c69bf55b6c202584dcf223f52ebd7dee58204f08"

# The compiled binaries don't provide sonames.
SOLIBS = "${SOLIBSDEV}"

INSANE_SKIP_${PN} = "ldflags"

# do not put the *.so into -dev package
FILES_${PN}-dev = ""
