# McsmCmd

一个在 MC Paper 服务端中，向 MCSM 实例发送指令的插件。

* 用法: /mcsmcmd <子服名称|组名称> <命令>

* 重载配置: /mcsmcmd reload

* 权限: mcsmcmd.use

* 配置文件: config.yml

```yaml
mcsm:
  api_url: "http://127.0.0.1:23333/"
  api_key: "your-api-key"
  instances:
    lobby: { uuid: "uuid-123", daemonId: "daemon-1" }
    game1: { uuid: "uuid-456", daemonId: "daemon-2" }
  groups:
    gameservers:
      - "lobby"
      - "game1"

```

By MeTerminator
@Necl Devlopment Team
@BlockLand Realms
