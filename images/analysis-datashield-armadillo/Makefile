#
# Copyright (c) 2023, TRE-FX Consortium Members
# Copyright (c) 2023-2025, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#

include ../settings.mk
include ../build.mk

PODMAN = podman
WGET   = wget
RM     = rm -f

IMAGE_NAME = docker.io/risbic/analysis-datashield-armadillo
IMAGE_TAG  = latest

MOLGENIS_ARMADILLO_JAR = molgenis-armadillo-4.13.1.jar
MOLGENIS_ARMADILLO_URL = https://github.com/molgenis/molgenis-service-armadillo/releases/download/v4.13.1/molgenis-armadillo-4.13.1.jar

all: $(MOLGENIS_ARMADILLO_JAR)
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f Containerfile .

$(MOLGENIS_ARMADILLO_JAR):
	$(WGET) -O $(MOLGENIS_ARMADILLO_JAR) $(MOLGENIS_ARMADILLO_URL)

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

clean:
	$(RM) $(MOLGENIS_ARMADILLO_JAR)

purge: clean
	$(PODMAN) $(POCMAN_IMAGE_RM) $(IMAGE_NAME):$(IMAGE_TAG)
