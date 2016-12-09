#!/bin/sh

[ -d /data/local/userinit.d ] || mkdir -p /data/local/userinit.d

if ! [ -f /data/local/userinit.d/99sshd ]
then
    cat /system/bin/start-ssh | \
        sed 's;/system/etc/ssh/sshd_config;/data/ssh/sshd_config;' > /data/local/userinit.d/99sshd
    chmod 755 /data/local/userinit.d/99sshd
fi