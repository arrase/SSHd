#!/bin/sh

DSA_KEY=/data/ssh/ssh_host_dsa_key
DSA_PUB_KEY=/data/ssh/ssh_host_dsa_key.pub
RSA_KEY=/data/ssh/ssh_host_rsa_key
RSA_PUB_KEY=/data/ssh/ssh_host_rsa_key.pub

if [ ! -f $DSA_KEY ]; then
    /system/bin/ssh-keygen -t dsa -f $DSA_KEY -N ""
    chmod 600 /$DSA_KEY
    chmod 644 $DSA_PUB_KEY
fi

if [ ! -f $RSA_KEY ]; then
    /system/bin/ssh-keygen -t rsa -f $RSA_KEY -N ""
    chmod 600 /$RSA_KEY
    chmod 644 $RSA_PUB_KEY
fi

if [ -d /data/ssh/empty ]; then
    mkdir -p /data/ssh/empty
    chmod 750 /data/ssh
    chmod 700 /data/ssh/empty
fi
