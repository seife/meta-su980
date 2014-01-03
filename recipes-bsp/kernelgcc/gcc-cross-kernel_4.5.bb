# kernel only needs C, no C++, FORTRAN, ...
LANGUAGES = "c"
# build fails with too new texinfo >= 5.0
# we don't want the documentation anyway, so just skip building it.
CACHED_CONFIGUREVARS += "MAKEINFO=missing"

require gcc-${PV}.inc
require gcc-cross4.inc

EXTRA_OECONF += "--disable-libunwind-exceptions \
                 --with-mpfr=${STAGING_DIR_NATIVE}${prefix_native} \
                 --with-system-zlib \
                 --with-as=${STAGING_BINDIR_TOOLCHAIN}/${TARGET_SYS}-as \
"

ARCH_FLAGS_FOR_TARGET += "-isystem${STAGING_DIR_TARGET}${target_includedir}"

do_install() {
	# oe_runmake 'DESTDIR=${D}' installdirs
	cd gcc; oe_runmake 'DESTDIR=${D}' installdirs install-common install-headers install-driver
	# remove everything but the versioned binary
	for i in gcc gcov gccbug; do
		rm ${D}${bindir}/${TARGET_PREFIX}$i
	done
}
