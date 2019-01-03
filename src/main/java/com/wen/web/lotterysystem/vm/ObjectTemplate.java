package com.wen.web.lotterysystem.vm;

import com.wen.web.lotterysystem.utils.VelocityUtil;
import org.apache.velocity.VelocityContext;

/**
 * 输出xml完整模板
 *
 * @author admin
 * @date 2018-12-3 11:01
 */
public class ObjectTemplate {
    private static final String vmPaht = "vm/xml_object.vm";
    private static final String vmMapPaht = "vm/mapping.vm";


    public static String getTemXmlObject(String objects) {
        VelocityContext ctx = new VelocityContext();
        ctx.put("Objects", objects);
        return VelocityUtil.getXmlString(ctx, vmPaht);
    }

    public static String getTemDeleteSeriesObject(String code) {
        VelocityContext ctx = new VelocityContext();
        ctx.put("ID", code);
        ctx.put("Code", code);
        return VelocityUtil.getXmlString(ctx, vmPaht);
    }

    public static String getTemProgramMovieMapping(String parentCode, String elementCode, String seq, String name) {
        VelocityContext ctx = new VelocityContext();
        ctx.put("Name", name);
        ctx.put("ParentType", "Program");
        ctx.put("ParentID", parentCode);
        ctx.put("ParentCode", parentCode);
        ctx.put("ElementType", "Movie");
        ctx.put("ElementID", elementCode);
        ctx.put("ElementCode", elementCode);
        ctx.put("Action", "xxxx");
        ctx.put("Type", "");
        ctx.put("Sequence", seq);
        ctx.put("ValidStart", "");
        ctx.put("ValidEnd", "");
        ctx.put("Result", 0);
        ctx.put("ErrorDescription", "");
        return VelocityUtil.getXmlString(ctx, vmMapPaht);
    }

    public static void main(String[] args) {
        String str = getTemProgramMovieMapping("name", "name", "name", "name");
        //System.out.println(str);
    }

    /**
     * 通过java模板工具生成xml文件的方式
     * 1.使用freemarker生成xml模板
     * 2.使用Velocity生成xml模板
     *
     */
}
