DESCRIPTION = "Linux kernel for ${MACHINE}"
SECTION = "kernel"
LICENSE = "GPLv2"

PATCHLEVEL ?= ".2"

SRC_URI[md5sum] = "bccca90a4bfd74b1335012e78d433cd5"
SRC_URI[sha256sum] = "590f0357e80eb8af5d391868aa403234021b7d445496960b744d6fe774193adc"

LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# By default, kernel.bbclass modifies package names to allow multiple kernels
# to be installed in parallel. We revert this change and rprovide the versioned
# package names instead, to allow only one kernel to be installed.
PKG_kernel-base = "kernel-base"
PKG_kernel-image = "kernel-image"
RPROVIDES_kernel-base = "kernel-${KERNEL_VERSION}"
RPROVIDES_kernel-image = "kernel-image-${KERNEL_VERSION}"

SRC_URI = " \
	${KERNELORG_MIRROR}/linux/kernel/v${PV}/linux-${PV}${PATCHLEVEL}.tar.bz2;name=kernel \
	file://defconfig \
	"

S = "${WORKDIR}/linux-${PV}"

inherit kernel

export OS = "Linux"
KERNEL_OBJECT_SUFFIX = "ko"
KERNEL_OUTPUT = "vmlinux"
KERNEL_IMAGETYPE = "vmlinux"
KERNEL_IMAGEDEST = "/tmp"

FILES_kernel-image = "${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}-${KERNEL_VERSION}"

do_configure_prepend() {
        oe_machinstall -m 0644 ${WORKDIR}/defconfig ${S}/.config
        oe_runmake oldconfig
}

do_install_append () {
	install -m 0644 vmlinux ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin
}

pkg_preinst_kernel-image () {
	[ -d /proc/stb ] && mount -t jffs2 mtd:'boot partition' /boot
	true
}

pkg_postinst_kernel-image () {
	[ -d /proc/stb ] && umount /boot
	true
}

pkg_prerm_kernel-image () {
	[ -d /proc/stb ] && mount -t jffs2 mtd:'boot partition' /boot
	true
}

pkg_postrm_kernel-image () {
	[ -d /proc/stb ] && umount /boot
	true
}
