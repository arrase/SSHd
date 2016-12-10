# Cyanogenmod SSHd Manager

Setup and manage sshd daemon for Cyanogenmod

### Requisites

Runs on CM10 or greater

### Usage examples

After start the daemon you can:

- Login:

```
    ssh -i .ssh/android shell@<device_ip>
```

- Backup SD:

```
    rsync -avz -e 'ssh -i .ssh/android' shell@<device_ip>:/sdcard/ sdcard
```