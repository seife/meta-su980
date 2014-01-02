#TARGET_ARCH = "arm"
#TARGET_FPU = "soft"
DEFAULTTUNE = "cortexa9"

require conf/machine/include/tune-cortexa9.inc

#TUNEVALID[cortexa9] = "Enable Cortex-A9 specific processor optimizations"
#
# not sure if we need this everywhere or just to compile the kernel...
TUNE_CCARGS += "${@bb.utils.contains("TUNE_FEATURES", "cortexa9", "-march=armv7-a -mtune=cortex-a9 -mfpu=vfpv3-d16 -mfloat-abi=softfp", "", d)}"
