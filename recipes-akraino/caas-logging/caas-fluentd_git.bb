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

SUMMARY = "Containers as a Service Fluentd component"
DESCRIPTION = "This contains the Fluentd container image, \
  and related deployment artifacts for the CaaS subsystem. \
"
HOMEPAGE = "https://github.com/fluent/fluentd"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2ee41112a44fe7014dce33e26468ba93"

PROTOCOL = "https"
SRCREV = "18a4bea203375dcc595992f2b5679348958e33d0"

SRC_URI = "git://gerrit.akraino.org/r/ta/caas-logging;protocol=${PROTOCOL};rev=${SRCREV}"

S = "${WORKDIR}/git"

inherit docker-build

MAJOR_VERSION = "1.6.3"
MINOR_VERSION = "0"

PV = "${MAJOR_VERSION}-${MINOR_VERSION}"

COMPONENT = "fluentd"
DOCKER_EXTRA_ARG += "--build-arg VERSION=${MAJOR_VERSION}"
