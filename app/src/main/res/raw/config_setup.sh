#!/bin/sh

if [ -d /data/ssh/empty ]
then
    mkdir -p /data/ssh/empty
    chmod 700 /data/ssh
    chmod 700 /data/ssh/empty
fi

if ! [ -f /data/ssh/sshd_config ]
then
    cat /system/etc/ssh/sshd_config | \
        sed 's/#PermitRootLogin yes$/PermitRootLogin no/' | \
        sed 's/#PubkeyAuthentication yes/PubkeyAuthentication yes/' | \
        sed 's/#PasswordAuthentication yes/PasswordAuthentication no/' | \
        sed 's/#PermitEmptyPasswords no/PermitEmptyPasswords no/' | \
        sed 's/#ChallengeResponseAuthentication yes/ChallengeResponseAuthentication no/' | \
        sed 's;/usr/libexec/sftp-server;internal-sftp;' > /data/ssh/sshd_config

    chmod 600 /data/ssh/sshd_config
fi

if [ -d /data/.ssh ]
then
    mkdir -p /data/.ssh
    chmod 700 /data/.ssh
    chown shell:shell /data/.ssh
fi