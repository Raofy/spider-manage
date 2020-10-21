## 添加job并启动

```text
{
  "jobGroup": 2,   //执行键主键id
  "jobCron" : "cron表达式",
  "jobDesc" : "任务描述",
  "executorRouteStrategy": "FIRST",
   // 执行器，任务Handler名称
    requestInfo.put("executorHandler", "xxxJobHandler");
    // todo 执行器，任务参数
    requestInfo.put("executorParam", "测试202006300943");
    // 阻塞处理策略
    requestInfo.put("executorBlockStrategy", "SERIAL_EXECUTION");
    // 任务执行超时时间，单位秒
    requestInfo.put("executorTimeout", 0);
    // 失败重试次数
    requestInfo.put("executorFailRetryCount", 1);
    // GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
    requestInfo.put("glueType", "BEAN");
    // GLUE备注
    requestInfo.put("glueRemark", "GLUE代码初始化");
    
    // 调度状态：0-停止，1-运行
    requestInfo.put("triggerStatus", 0);
    // 上次调度时间
    requestInfo.put("triggerLastTime", 0);
    // 下次调度时间
    requestInfo.put("triggerNextTime", 0);

}
```