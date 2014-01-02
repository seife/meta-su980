# kernel only needs C, no C++, FORTRAN, ...
LANGUAGES = "c"

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
