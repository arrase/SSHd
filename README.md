# SSHd Manager

Setup and manage sshd daemon for LineageOs and other roms with sshd built-in.

### Requisites

Needs root

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
