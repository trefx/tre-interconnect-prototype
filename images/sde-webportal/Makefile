#
# Copyright (c) 2023, TRE-FX Consortium Members
# Copyright (c) 2023-2024, Arjuna Technologies Limited, Newcastle upon Tyne, Tyne and Wear, UK.
#
#
# In IP4 only environment need "export NODE_OPTIONS=--dns-result-order=ipv4first" 
#

include ../settings.mk
include ../build.mk

RMDIR  = rm -rf
NPM    = npm
NG     = ng
PODMAN = podman

IMAGE_NAME = $(IMAGE_REPO)/sde-webportal
# IMAGE_TAG from settings.mk

all: dist
	$(PODMAN) build -t $(IMAGE_NAME):$(IMAGE_TAG) -f Containerfile .

push: all
	$(PODMAN) image push $(IMAGE_NAME):$(IMAGE_TAG)

dist: node_modules
	$(NG) build

node_modules:
	$(NPM) install

clean:
	$(RMDIR) dist

purge: clean
	$(RMDIR) node_modules
	$(NG) cache clean
	$(PODMAN) $(POCMAN_IMAGE_RM) $(IMAGE_NAME):$(IMAGE_TAG)
