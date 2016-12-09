#!/bin/sh

if [ -d /data/ssh/empty ]
then
    mkdir -p /data/ssh/empty
fi

chmod 700 /data/ssh
chmod 700 /data/ssh/empty

if [ -f /data/ssh/sshd_config ]
then
    chmod 600 /data/ssh/sshd_config
fi

if ! [ -d /data/.ssh ]
then
    mkdir -p /data/.ssh
fi

chmod 700 /data/.ssh
chown shell:shell /data/.ssh