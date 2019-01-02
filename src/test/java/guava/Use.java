package guava;

import com.jfinal.plugin.cron4j.ITask;

public class Use {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classNameStr = "com.mycurdpro.system.task.TestTask";
        Class className = Class.forName(classNameStr);
        Object instance = className.newInstance();
        System.out.println(instance instanceof ITask);
        System.out.println(instance instanceof Runnable);

        System.out.println(Use.class.getPackage());
        System.out.println(Use.class.getName());
    }
}
