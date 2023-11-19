package org.mapper;

import org.entity.Monk;

import java.util.List;

/**
 * Karl Rules!
 * 2023/11/16
 * now File Encoding is UTF-8
 */
public interface MonkMapper {
//    查询
    public Monk getMonkById(Integer id);
//    添加
    public void addMonk(Monk monk);
//    删除
    public void delMonk(Integer id);
//    修改
    public void updateMonk(Monk monk);
//    查询所有
    public List<Monk> findAllMonk();


}
