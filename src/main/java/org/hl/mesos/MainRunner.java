/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: MainRunner.java 
 * @Prject: mesos_framework_demo
 * @Package: org.hl.mesos 
 * @Description: TODO
 * @author: hl   
 * @date: 2016年11月8日 下午6:46:59 
 * @version: V1.0   
 */
package org.hl.mesos;

import org.apache.mesos.MesosSchedulerDriver;
import org.apache.mesos.Protos;
import org.apache.mesos.Protos.FrameworkInfo;
import org.apache.mesos.Protos.ExecutorInfo;
import org.apache.mesos.Protos.CommandInfo;
import org.apache.mesos.Scheduler;

/** 
 * @ClassName: MainRunner 
 * @Description: TODO
 * @author: hl
 * @date: 2016年11月8日 下午6:46:59  
 */

public class MainRunner
{
    
    static String frameworkName = "framework-example";
    
    static String executorName = "ExampleExecutor";
    
    static String path = "/home/hl/mesos_install/target/example-framework-1.0-SNAPSHOT-jar-with-dependencies.jar";
    
    static String command =
        "java -cp example-framework-1.0-SNAPSHOT-jar-with-dependencies.jar org.hl.mesos.ExampleExecutor";
    
    private static FrameworkInfo getFrameworkInfo()
    {
        FrameworkInfo.Builder builder = FrameworkInfo.newBuilder();
        builder.setFailoverTimeout(120000);
        builder.setUser("");
        builder.setName(frameworkName);
        return builder.build();
    }
    
    private static CommandInfo.URI getUri()
    {
        CommandInfo.URI.Builder uriBuilder = CommandInfo.URI.newBuilder();
        uriBuilder.setValue(path);
        uriBuilder.setExtract(false);
        return uriBuilder.build();
    }
    
    private static CommandInfo getCommandInfo()
    {
        CommandInfo.Builder cmdInfoBuilder = Protos.CommandInfo.newBuilder();
        cmdInfoBuilder.addUris(getUri());
        cmdInfoBuilder.setValue(command);
        return cmdInfoBuilder.build();
    }
    
    private static ExecutorInfo getExecutorInfo()
    {
        ExecutorInfo.Builder builder = ExecutorInfo.newBuilder();
        builder.setExecutorId(Protos.ExecutorID.newBuilder().setValue(executorName));
        builder.setCommand(getCommandInfo());
        builder.setName(executorName);
        builder.setSource("java");
        return builder.build();
    }
    
    private static void runFramework(String mesosMaster)
    {
        Scheduler scheduler = new ExampleScheduler(getExecutorInfo());
        MesosSchedulerDriver driver = new MesosSchedulerDriver(scheduler, getFrameworkInfo(), mesosMaster);
        int status = driver.run() == Protos.Status.DRIVER_STOPPED ? 0 : 1;
        
        driver.stop();
        System.exit(status);
    }
    
    public static void main(String[] args)
        throws Exception
    {
        runFramework(args[0]);
    }
}
