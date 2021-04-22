package com.yw.custommutilimageadapter.bean

/**
 *
 * @ProjectName:    CustomMutilImageAdapter
 * @Package:        com.yw.custommutilimageadapter.bean
 * @ClassName:      FoldBean
 * @Description:     java类作用描述
 * @Author:         wei.yang
 * @CreateDate:     2020/11/26 19:33
 * @UpdateUser:     更新者：wei.yang
 * @UpdateDate:     2020/11/26 19:33
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 */
class FoldBean(
    /**
     * 1.网络图片 2.本地资源
     */
    var type: Int=1,
    /**
     * 本地资源id
     */
    var resId: Int = 0,
    /**
     * 网络图片资源路径
     */
    var url: String=""

) {
}