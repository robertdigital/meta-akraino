#
# Copyright (C) 2019 Wind River Systems, Inc.
#
#  Licensed under the Apache License, Version 2.0 (the "License");
#  you may not use this file except in compliance with the License.
#  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
#  limitations under the License.

DESCRIPTION = "This RPM contains Access Management component for Akraino REC blueprint"
HOMEPAGE = "https://wiki.akraino.org/pages/viewpage.action?pageId=6128402"
SECTION = "devel/python"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

STABLE = "master"
PROTOCOL = "https"
BRANCH = "master"
SRCREV = "34040e04398054a8ab335e3b8ff5dae7ddcd3472"
S = "${WORKDIR}/git/src"

SRC_URI = "git://gerrit.akraino.org/r/ta/access-management.git;protocol=${PROTOCOL};rev=${SRCREV};branch=${BRANCH}"

inherit setuptools useradd akraino-version systemd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-r access-manager"

do_install_append() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/git/systemd/auth-server.service ${D}${systemd_system_unitdir}/
    install -m 0700 -d ${D}/var/log/access_management
    install -D -m 755 ${WORKDIR}/git/secrets/am-secrets.yaml ${D}/${sysconfdir}/required-secrets/am-secrets.yaml
}

SYSTEMD_SERVICE_${PN} = "auth-server.service"

FILES_${PN} += "\
    ${systemd_system_unitdir}/auth-server.service \
"


DEPENDS += " \
        python-pip \
        python-pbr-native \
        "

RDEPENDS_${PN} += " \
        python-flask \
        python-flask-restful \
        python-configparser \
        mod-wsgi \
        python-peewee \
        "
