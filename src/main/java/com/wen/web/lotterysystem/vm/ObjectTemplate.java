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

    public static void main(String[] args) {
        String str = getTemDeleteSeriesObject("name");
        System.out.println(str);
    }
}
