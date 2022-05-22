import org.springframework.beans.BeanUtils;

/**
 * @author wsk
 * @date 2020/3/15 15:33
 */
public class T2 {

    public static void main(String[] args) {
        t1 t1 = new t1();
        t1.setAge(11);
        t1.setName("zhangzhao");

//        StringBuffer sb = new StringBuffer();
//        StringBuffer sb1 = new StringBuffer();
        toC(t1);
//        toCc(sb);
    }
    public static <T> void  toC(T obj){
        t2 t2 = new t2();
        BeanUtils.copyProperties(obj,t2);
        System.out.println(t2.toString());
    }
    public static  void  toCc(Object obj){
        StringBuffer obj1 = (StringBuffer) obj;
        obj1.append("1");
        System.out.println(obj1.toString());
        System.out.println(obj.getClass());
        System.out.println(obj1.getClass());
    }

    static class t1{
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    static class t2{
        private String name;
        private Integer max;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        @Override
        public String toString() {
            return "t2{" +
                    "name='" + name + '\'' +
                    ", max=" + max +
                    '}';
        }
    }
}