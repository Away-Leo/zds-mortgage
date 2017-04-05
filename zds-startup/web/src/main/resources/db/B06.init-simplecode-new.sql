


/*期限单位*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('0931', 'F', '0', '0931', '期限单位', '期限单位', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '0931001', 'F', '0', '0931001', '日', 'T', '日', '99', '0931001', 'F', 'F', '0931');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '0931002', 'F', '0', '0931002', '月', 'T', '月', '99', '0931002', 'F', 'F', '0931');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '0931003', 'F', '0', '0931003', '年', 'T', '年', '90', '0931003', 'F', 'F', '0931');
/*是否实际用款人*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('actualUsePerson', 'F', '0', 't0102', '是否实际用款人', '是否实际用款人', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 't01021', 'F', '0', 't01021', '是', 'T', '是', '0', 't01021', 'F', 'F', 'actualUsePerson');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 't01022', 'F', '0', 't01022', '否', 'T', '否', '0', 't01022', 'F', 'F', 'actualUsePerson');
/*地址类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('addressType', 'F', '0', 't093', '地址类型', '地址类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 't0930', 'F', '0', 't0930', '家庭地址', 'T', '家庭地址', '0', 't0930', 'F', 'F', 'addressType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 't0931', 'F', '0', 't0931', '户籍地址', 'T', '户籍地址', '0', 't0931', 'F', 'F', 'addressType');
/*申请单*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('applayFormCode', 'F', '0', 'applayFormCode', '申请单', '申请单', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'applayFormCode01', 'F', '0', 'applayFormCode01', '项目申请', 'T', '项目申请', '0', 'applayFormCode01', 'F', 'F', 'applayFormCode');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'applayFormCode02', 'F', '0', 'applayFormCode02', '准入申请', 'T', '准入申请', '0', 'applayFormCode02', 'F', 'F', 'applayFormCode');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'applayFormCode03', 'F', '0', 'applayFormCode03', '合同申请', 'T', '合同申请', '0', 'applayFormCode03', 'F', 'F', 'applayFormCode');
/*档案等级*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('archivesLevel', 'F', '0', 'archivesLevel', '档案等级', '文件类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'archivesLevel01', 'F', '0', 'BILL_ONE', '一级', 'T', '一级', '99', 'BILL_ONE', 'F', 'F', 'archivesLevel');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'archivesLevel02', 'F', '0', 'BILL_TWO', '二级', 'T', '二级', '99', 'BILL_TWO', 'F', 'F', 'archivesLevel');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'archivesLevel03', 'F', '0', 'BILL_THREE', '三级', 'T', '三级', '99', 'BILL_THREE', 'F', 'F', 'archivesLevel');
/*文件类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('archivesType', 'F', '0', 'archivesType', '文件类型', '文件类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'archivesType01', 'F', '0', 'BILL_ORIGINAL', '原件', 'T', '原件', '99', 'BILL_ORIGINAL', 'F', 'F', 'archivesType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'archivesType02', 'F', '0', 'BILL_COPIES', '复印件', 'T', '复印件', '99', 'BILL_COPIES', 'F', 'F', 'archivesType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'archivesType03', 'F', '0', 'BILL_PICTURE', '照片件', 'T', '照片件', '99', 'BILL_PICTURE', 'F', 'F', 'archivesType');
/*职业类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('careerType', 'F', '0', 'c10100', '职业类型', '职业类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'c101001', 'F', '0', 'c101001', '企业主', 'T', '企业主', '0', 'c101001', 'F', 'F', 'careerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'c101002', 'F', '0', 'c101002', '上班族', 'T', '上班族', '0', 'c101002', 'F', 'F', 'careerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'c101003', 'F', '0', 'c101003', '无', 'T', '无', '0', 'c101003', 'F', 'F', 'careerType');

/*收费类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('chargeClass', 'F', '0', 'chargeClass', '收费类型', '收费类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'chargeClass01', 'F', '0', 'chargeClass01', '赎楼回款', 'T', '赎楼回款', '0', 'chargeClass01', 'F', 'F', 'chargeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'chargeClass02', 'F', '0', 'chargeClass02', '赎楼资金', 'T', '赎楼资金', '0', 'chargeClass02', 'F', 'F', 'chargeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'chargeClass03', 'F', '0', 'chargeClass03', '押金费用', 'T', '押金费用', '0', 'chargeClass03', 'F', 'F', 'chargeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'chargeClass04', 'F', '0', 'chargeClass04', '快贷资金', 'T', '快贷资金', '0', 'chargeClass04', 'F', 'F', 'chargeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'chargeClass05', 'F', '0', 'chargeClass05', '银行付费', 'T', '银行付费', '0', 'chargeClass05', 'F', 'F', 'chargeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'chargeClass06', 'F', '0', 'chargeClass06', '代收代付', 'T', '代收代付', '0', 'chargeClass06', 'F', 'F', 'chargeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'chargeClass07', 'F', '0', 'chargeClass07', '前端收入', 'T', '前端收入', '0', 'chargeClass07', 'F', 'F', 'chargeClass');
/*企业类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('ct00100', 'F', '0', 'ct00100', '企业类型', '企业类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'ct001001', 'F', '0', 'ct001001', '银行', 'T', '银行', '0', 'ct001001', 'F', 'F', 'ct00100');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'ct001002', 'F', '0', 'ct001002', '往来单位', 'T', '往来单位', '0', 'ct001002', 'F', 'F', 'ct00100');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'ct001003', 'F', '0', 'ct001003', '中介', 'T', '中介', '0', 'ct001003', 'F', 'F', 'ct00100');
/*合作单位归属*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('contactCompanyBelong', 'F', '0', 'cb00100', '合作单位归属', '合作单位归属', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'cb001001', 'F', '0', 'cb001001', '机构', 'T', '机构', '0', 'cb001001', 'F', 'F', 'contactCompanyBelong');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'cb001002', 'F', '0', 'cb001002', '部门', 'T', '部门', '0', 'cb001002', 'F', 'F', 'contactCompanyBelong');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'cb001003', 'F', '0', 'cb001003', '人员', 'T', '人员', '0', 'cb001003', 'F', 'F', 'contactCompanyBelong');
/*联系类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('contactType', 'F', '0', 'c0115', '联系类型', '联系类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'c01151', 'F', '0', 'c01151', '手机电话', 'T', '手机电话', '0', 'c01151', 'F', 'F', 'contactType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'c01152', 'F', '0', 'c01152', '家庭电话', 'T', '家庭电话', '0', 'c01152', 'F', 'F', 'contactType');

/*证件类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('credentiaType', 'F', '0', '060600', '证件类型', '证件类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060001', 'F', '0', '06060001', '身份证', 'T', '身份证', '0', '06060001', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '060600010', 'F', '0', '060600010', '警官证', 'T', '警官证', '0', '060600010', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '060600011', 'F', '0', '060600011', '香港身份证', 'T', '香港身份证', '0', '060600011', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '060600012', 'F', '0', '060600012', '澳门身份证', 'T', '澳门身份证', '0', '060600012', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '060600013', 'F', '0', '060600013', '台湾身份证', 'T', '台湾身份证', '0', '060600013', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '060600014', 'F', '0', '060600014', '其他证件', 'T', '其他证件', '0', '060600014', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060002', 'F', '0', '06060002', '户口簿', 'T', '户口簿', '0', '06060002', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060003', 'F', '0', '06060003', '护照', 'T', '护照', '0', '06060003', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060004', 'F', '0', '06060004', '军官证', 'T', '军官证', '0', '06060004', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060005', 'F', '0', '06060005', '士兵证', 'T', '士兵证', '0', '06060005', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060006', 'F', '0', '06060006', '港澳居民来往内地通行证', 'T', '港澳居民来往内地通行证', '0', '06060006', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060007', 'F', '0', '06060007', '台湾同胞来往内地通行证', 'T', '台湾同胞来往内地通行证', '0', '06060007', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060008', 'F', '0', '06060008', '临时身份证', 'T', '临时身份证', '0', '06060008', 'F', 'F', 'credentiaType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '06060009', 'F', '0', '06060009', '外国人居留证', 'T', '外国人居留证', '0', '06060009', 'F', 'F', 'credentiaType');

/*教育程度*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('degree', 'F', '0', 'e030300', '教育程度', '教育程度', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303001', 'F', '0', 'e0303001', '研究生', 'T', '研究生', '0', 'e0303001', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e03030010', 'F', '0', 'e03030010', '未知', 'T', '未知', '0', 'e03030010', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303002', 'F', '0', 'e0303002', '大学本科', 'T', '大学本科', '0', 'e0303002', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303003', 'F', '0', 'e0303003', '大学专科和专科学校', 'T', '大学专科和专科学校', '0', 'e0303003', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303004', 'F', '0', 'e0303004', '中等专业学校或中等技术学校', 'T', '中等专业学校或中等技术学校', '0', 'e0303004', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303005', 'F', '0', 'e0303005', '技术学校', 'T', '技术学校', '0', 'e0303005', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303006', 'F', '0', 'e0303006', '高中', 'T', '高中', '0', 'e0303006', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303007', 'F', '0', 'e0303007', '初中', 'T', '初中', '0', 'e0303007', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303008', 'F', '0', 'e0303008', '小学', 'T', '小学', '0', 'e0303008', 'F', 'F', 'degree');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'e0303009', 'F', '0', 'e0303009', '文盲或半文盲', 'T', '文盲或半文盲', '0', 'e0303009', 'F', 'F', 'degree');

/*业务设置-特批事项类型*/
INSERT INTO ess_codecategory(id,isDeleted,version,categoryCd,categoryNm,codeGenRuler,description,isCommon,isEnabled,isTree,orderNo )  VALUES ('YWDM0077', 'F', 1, 'YWDM0077', '特批事项', NULL, '特批事项', 'T', 'T', 'F', NULL);
INSERT INTO ess_simplecode(isTree,id,isDeleted,version,code,desc_,isEnable,name,fullcode,codeCategory_id,parent_id,isDefault,dispOrder,isFixed) VALUES (0, 'YWDM007701','F', 0, 'YWDM007701', '', 'T', '申请人', 'YWDM007701', 'YWDM0077', NULL, 'T', '595', 'T');
INSERT INTO ess_simplecode(isTree,id,isDeleted,version,code,desc_,isEnable,name,fullcode,codeCategory_id,parent_id,isDefault,dispOrder,isFixed) VALUES (0, 'YWDM007702','F', 0, 'YWDM007702', '', 'T', '抵押物', 'YWDM007702', 'YWDM0077', NULL, 'T', '596', 'T');
INSERT INTO ess_simplecode(isTree,id,isDeleted,version,code,desc_,isEnable,name,fullcode,codeCategory_id,parent_id,isDefault,dispOrder,isFixed) VALUES (0, 'YWDM007703','F', 0, 'YWDM007703', '', 'T', '资信调查', 'YWDM007703', 'YWDM0077', NULL, 'T', '597', 'T');
INSERT INTO ess_simplecode(isTree,id,isDeleted,version,code,desc_,isEnable,name,fullcode,codeCategory_id,parent_id,isDefault,dispOrder,isFixed) VALUES (0, 'YWDM007704','F', 0, 'YWDM007704', '', 'T', '审批材料', 'YWDM007704', 'YWDM0077', NULL, 'T', '598', 'T');
INSERT INTO ess_simplecode(isTree,id,isDeleted,version,code,desc_,isEnable,name,fullcode,codeCategory_id,parent_id,isDefault,dispOrder,isFixed) VALUES (0, 'YWDM007705','F', 0, 'YWDM007705', '', 'T', '其它事项', 'YWDM007705', 'YWDM0077', NULL, 'T', '599', 'T');
INSERT INTO ess_simplecode(isTree,id,isDeleted,version,code,desc_,isEnable,name,fullcode,codeCategory_id,parent_id,isDefault,dispOrder,isFixed) VALUES (0, 'YWDM007706','F', 0, 'YWDM007706', '', 'T', '风险措施', 'YWDM007706', 'YWDM0077', NULL, 'T', '600', 'T');
/*费用项*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('feeCd', 'F', '0', 'feeCd', '费用项', '费用项', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'feeCd01', 'F', '0', 'feeCd01', '本金', 'T', '本金', '0', 'feeCd01', 'F', 'F', 'feeCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'feeCd02', 'F', '0', 'feeCd02', '利息', 'T', '利息', '0', 'feeCd02', 'F', 'F', 'feeCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'feeCd03', 'F', '0', 'feeCd03', '管理费', 'T', '管理费', '0', 'feeCd03', 'F', 'F', 'feeCd');
/*可接受付佣方式*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('fyfs', 'F', '0', 'sjyw', '可接受付佣方式', '可接受付佣方式', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'fyfs001', 'F', '0', '1', '月结', 'T', '月结', '0', '1', 'F', 'F', 'fyfs');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'fyfs002', 'F', '0', '2', '递件', 'T', '递件', '0', '2', 'F', 'F', 'fyfs');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'fyfs003', 'F', '0', '3', '放款', 'T', '放款', '0', '3', 'F', 'F', 'fyfs');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'fyfs004', 'F', '0', '4', '抵押', 'T', '抵押', '0', '4', 'F', 'F', 'fyfs');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'fyfs005', 'F', '0', '5', '其他', 'T', '其他', '0', '5', 'F', 'F', 'fyfs');
/*返佣类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('fylx', 'F', '0', 'fylx', '返佣类型', '返佣类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'fylx001', 'F', '0', '1', '返佣比例', 'T', '返佣比例', '0', '1', 'F', 'F', 'fylx');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'fylx002', 'F', '0', '2', '返佣金额', 'T', '返佣金额', '0', '2', 'F', 'F', 'fylx');
/*性别*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('gender', 'F', '0', 'g0156', '性别', '性别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g01561', 'F', '0', 'g01561', '男', 'T', '男', '0', 'g01561', 'F', 'F', 'gender');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g01562', 'F', '0', 'g01562', '女', 'T', '女', '0', 'g01562', 'F', 'F', 'gender');
/*是否机构公用*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('gggy', 'F', '0', 'gggy', '是否机构公用', '是否机构公用', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'gggy001', 'F', '0', '1', '机构公用', 'T', '机构公用', '0', '1', 'F', 'F', 'gggy');
/*评级*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('gradeClass', 'F', '0', 'gradeClass', '评级', '评级', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'gradeClass02', 'F', '0', 'gradeClass02', 'A', 'T', 'A', '0', 'gradeClass02', 'F', 'F', 'gradeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'gradeClass01', 'F', '0', 'gradeClass01', 'A+', 'T', 'A+', '0', 'gradeClass01', 'F', 'F', 'gradeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'gradeClass04', 'F', '0', 'gradeClass04', 'B', 'T', 'B', '0', 'gradeClass04', 'F', 'F', 'gradeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'gradeClass03', 'F', '0', 'gradeClass03', 'B+', 'T', 'B+', '0', 'gradeClass03', 'F', 'F', 'gradeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'gradeClass06', 'F', '0', 'gradeClass06', 'C', 'T', 'C', '0', 'gradeClass06', 'F', 'F', 'gradeClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'gradeClass05', 'F', '0', 'gradeClass05', 'C+', 'T', 'C+', '0', 'gradeClass05', 'F', 'F', 'gradeClass');
/*行业*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('hangye', 'F', '0', 'hangye', '行业', '行业', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'hangye001', 'F', '0', 'hangye001', '行业', 'T', '行业', '0', 'hangye001', 'F', 'F', 'hangye');
/*行业类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('industryType', 'F', '0', 'i00100', '行业类型', '行业类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001001', 'F', '0', 'i001001', '农、林、牧、渔业', 'T', '农、林、牧、渔业', '0', 'i001001', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010010', 'F', '0', 'i0010010', '金融业', 'T', '金融业', '0', 'i0010010', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010011', 'F', '0', 'i0010011', '房地产业', 'T', '房地产业', '0', 'i0010011', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010012', 'F', '0', 'i0010012', '租赁和商务服务业', 'T', '租赁和商务服务业', '0', 'i0010012', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010013', 'F', '0', 'i0010013', '科学研究、技术服务业和地质勘察业', 'T', '科学研究、技术服务业和地质勘察业', '0', 'i0010013', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010014', 'F', '0', 'i0010014', '水利、环境和公共设施管理业', 'T', '水利、环境和公共设施管理业', '0', 'i0010014', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010015', 'F', '0', 'i0010015', '居民服务和其他服务业', 'T', '居民服务和其他服务业', '0', 'i0010015', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010016', 'F', '0', 'i0010016', '教育', 'T', '教育', '0', 'i0010016', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010017', 'F', '0', 'i0010017', '卫生、社会保障和社会福利业', 'T', '卫生、社会保障和社会福利业', '0', 'i0010017', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010018', 'F', '0', 'i0010018', '文化、体育和娱乐业', 'T', '文化、体育和娱乐业', '0', 'i0010018', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010019', 'F', '0', 'i0010019', '公共管理和社会组织', 'T', '公共管理和社会组织', '0', 'i0010019', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001002', 'F', '0', 'i001002', '采掘业', 'T', '采掘业', '0', 'i001002', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010020', 'F', '0', 'i0010020', '国际组织', 'T', '国际组织', '0', 'i0010020', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010021', 'F', '0', 'i0010021', '未知', 'T', '未知', '0', 'i0010021', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i0010022', 'F', '0', 'i0010022', '建筑业', 'T', '建筑业', '0', 'i0010022', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001003', 'F', '0', 'i001003', '制造业', 'T', '制造业', '0', 'i001003', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001004', 'F', '0', 'i001004', '电力、燃气及水的生产和供应业', 'T', '电力、燃气及水的生产和供应业', '0', 'i001004', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001006', 'F', '0', 'i001006', '交通运输、仓储和邮政业', 'T', '交通运输、仓储和邮政业', '0', 'i001006', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001007', 'F', '0', 'i001007', '信息传输、计算机服务和软件业', 'T', '信息传输、计算机服务和软件业', '0', 'i001007', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001008', 'F', '0', 'i001008', '批发和零售业', 'T', '批发和零售业', '0', 'i001008', 'F', 'F', 'industryType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'i001009', 'F', '0', 'i001009', '住宿和餐饮业', 'T', '住宿和餐饮业', '0', 'i001009', 'F', 'F', 'industryType');

/*业务设置-诉讼资料配置类别liaoguowei*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('litigationFileType', 'F', '0', 'litigationFileType', '类别', '类别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'litigationFileType1', 'F', '0', 'litigationFileType001', '立案证明', 'T', '立案证明', '0', 'litigationFileType001', 'F', 'F', 'litigationFileType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'litigationFileType2', 'F', '0', 'litigationFileType002', '诉讼材料', 'T', '诉讼材料', '0', 'litigationFileType002', 'F', 'F', 'litigationFileType');
/*居住年限*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('liveAge', 'F', '0', 'y00100', '居住年限', '居住年限', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001001', 'F', '0', 'y001001', '1年', 'T', '1年', '0', 'y001001', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y0010010', 'F', '0', 'y0010010', '10年', 'T', '10年', '0', 'y0010010', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y0010011', 'F', '0', 'y0010011', '10年以上', 'T', '10年以上', '0', 'y0010011', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001002', 'F', '0', 'y001002', '2年', 'T', '2年', '0', 'y001002', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001003', 'F', '0', 'y001003', '3年', 'T', '3年', '0', 'y001003', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001004', 'F', '0', 'y001004', '4年', 'T', '4年', '0', 'y001004', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001005', 'F', '0', 'y001005', '5年', 'T', '5年', '0', 'y001005', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001006', 'F', '0', 'y001006', '6年', 'T', '6年', '0', 'y001006', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001007', 'F', '0', 'y001007', '7年', 'T', '7年', '0', 'y001007', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001008', 'F', '0', 'y001008', '8年', 'T', '8年', '0', 'y001008', 'F', 'F', 'liveAge');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'y001009', 'F', '0', 'y001009', '9年', 'T', '9年', '0', 'y001009', 'F', 'F', 'liveAge');
/*客户资料*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('maclass1', 'F', '0', 'maclass1', '客户资料', '客户资料', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client1', 'F', '0', '5', '本人拍照', 'T', '本人拍照', '0', 'brpz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client10', 'F', '0', '38', '卖方基本资料', 'T', '卖方基本资料', '0', 'mfjb', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client11', 'F', '0', '75', '原贷款资料', 'T', '原贷款资料', '0', 'ydkzl', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client12', 'F', '0', '76', '资产证明', 'T', '资产证明', '0', 'zczm', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client13', 'F', '0', '80', '申请人征信身份证', 'T', '申请人征信身份证', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client14', 'F', '0', '80', '申请人征信授权书', 'T', '申请人征信授权书', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client15', 'F', '0', '80', '申请人配偶征信身份证', 'T', '申请人配偶征信身份证', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client16', 'F', '0', '80', '申请人配偶征信授权书', 'T', '申请人配偶征信授权书', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client17', 'F', '0', '80', '共同借款人征信身份证', 'T', '共同借款人征信身份证', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client18', 'F', '0', '80', '共同借款人征信授权书', 'T', '共同借款人征信授权书', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client19', 'F', '0', '80', '共同借款人配偶征信身份证', 'T', '共同借款人配偶征信身份证', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client2', 'F', '0', '77', '贷款用途', 'T', '贷款用途', '0', 'dkyt', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client20', 'F', '0', '80', '共同借款人配偶征信授权书', 'T', '共同借款人配偶征信授权书', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client21', 'F', '0', '80', '担保人征信身份证', 'T', '担保人征信身份证', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client22', 'F', '0', '80', '担保人征信授权书', 'T', '担保人征信授权书', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client23', 'F', '0', '80', '担保人配偶征信身份证', 'T', '担保人配偶征信身份证', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client24', 'F', '0', '80', '担保人配偶征信授权书', 'T', '担保人配偶征信授权书', '0', 'rhzysfz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client3', 'F', '0', '78', '担保人资料', 'T', '担保人资料', '0', 'dbrzl', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client4', 'F', '0', '17', '抵押物照片', 'T', '抵押物照片', '0', 'dywz', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client5', 'F', '0', '92', '董事会决议、股东决议、担保函', 'T', '董事会决议、股东决议、担保函', '0', 'dshjy', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client6', 'F', '0', '30', '家访照片', 'T', '家访照片', '0', 'jfzp', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client7', 'F', '0', '', '公司照片', 'T', '公司照片', '0', 'gszp', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client8', 'F', '0', '74', '借款人公司资料', 'T', '借款人公司资料', '0', 'jkrgszl', 'F', 'F', 'maclass1');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_client9', 'F', '0', '32', '借款人或买方基本资料（身份证、户口本、配偶身份证、结婚证等）', 'T', '借款人或买方基本资料（身份证、户口本、配偶身份证、结婚证等）', '0', 'jkrj', 'F', 'F', 'maclass1');
/*合同类*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('maclass2', 'F', '0', 'maclass2', '合同类', '合同类', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract1', 'F', '0', '', '面签视频', 'T', '面签视频', '0', 'mqsp', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract10', 'F', '0', '20', '反担保协议', 'T', '反担保协议', '0', 'fdbx', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract11', 'F', '0', '27', '划款授权书(划出)', 'T', '划款授权书(划出)', '0', 'hksq1', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract12', 'F', '0', '28', '划款授权书(划回)', 'T', '划款授权书(划回)', '0', 'hksq2', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract13', 'F', '0', '95', '还款计划表', 'T', '还款计划表', '0', 'hkjhb', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract14', 'F', '0', '96', '机构总担保函', 'T', '机构总担保函', '0', 'jgzdbh', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract15', 'F', '0', '45', '借款合同', 'T', '借款合同', '0', 'sjjk', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract16', 'F', '0', '98', '借款合同---补充合同', 'T', '借款合同---补充合同', '0', 'jkht', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract17', 'F', '0', '47', '实际借款人借款借据', 'T', '实际借款人借款借据', '0', 'sjjj', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract18', 'F', '0', '57', '网签合同', 'T', '网签合同', '0', 'wqht', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract19', 'F', '0', '99', '委托扣款授权书', 'T', '委托扣款授权书', '0', 'wtkksqs', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract2', 'F', '0', '2', '保证金协议', 'T', '保证金协议', '0', 'bzjx', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract20', 'F', '0', '69', '债权抵押权转让声明书', 'T', '债权抵押权转让声明书', '0', 'zqzy', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract21', 'F', '0', '70', '债权抵押权转让通知书', 'T', '债权抵押权转让通知书', '0', 'zqtzs', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract22', 'F', '0', '71', '债权抵押转让合同', 'T', '债权抵押转让合同', '0', 'zqdy', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract23', 'F', '0', '100', '债务声明书', 'T', '债务声明书', '0', 'zwsms', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract24', 'F', '0', '72', '注销我司抵押相关文件', 'T', '注销我司抵押相关文件', '0', 'zxdy', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract25', 'F', '0', '101', '资金监管协议', 'T', '资金监管协议', '0', 'zjjg', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract26', 'F', '0', '105', '贷后承诺函', 'T', '贷后承诺函', '0', 'dhcnh', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract27', 'F', '0', '216', '贷款用途复核表', 'T', '贷款用途复核表', '0', 'dkytfhb', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract28', 'F', '0', '219', '代办通知函', 'T', '代办通知函', '0', 'dbtzh', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract3', 'F', '0', '10', '代办服务合同', 'T', '代办服务合同', '0', 'dbfw', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract4', 'F', '0', '12', '担保函(客户保证人)', 'T', '担保函(客户保证人)', '0', 'dbh1', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract5', 'F', '0', '13', '担保函(我司阶段性担保)', 'T', '担保函(我司阶段性担保)', '0', 'dbh2', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract6', 'F', '0', '14', '担保函(我司全程担保)', 'T', '担保函(我司全程担保)', '0', 'dbh3', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract7', 'F', '0', '15', '抵押合同', 'T', '抵押合同', '0', 'dyht', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract8', 'F', '0', '92', '抵押人声明书、承诺书', 'T', '抵押人声明书、承诺书', '0', 'dyrsms', 'F', 'F', 'maclass2');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_contract9', 'F', '0', '18', '抵押注销证明', 'T', '抵押注销证明', '0', 'dyzx', 'F', 'F', 'maclass2');
/*内审文件夹*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('maclass3', 'F', '0', 'maclass3', '内审文件夹', '内审文件夹', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_folder1', 'F', '0', '23', '风控审批表', 'T', '风控审批表', '0', 'fksp', 'F', 'F', 'maclass3');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_folder2', 'F', '0', '51', '特殊个案申请表', 'T', '特殊个案申请表', '0', 'tsga', 'F', 'F', 'maclass3');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_folder3', 'F', '0', '62', '业务委托及承接单', 'T', '业务委托及承接单', '0', 'ywwt', 'F', 'F', 'maclass3');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_folder4', 'F', '0', '218', '贷款申请表', 'T', '贷款申请表', '0', 'dksqb', 'F', 'F', 'maclass3');
/*权证类*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('maclass4', 'F', '0', 'maclass4', '权证类', '权证类', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant1', 'F', '0', '4', '保证人征信', 'T', '保证人征信', '0', 'bzrz', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant10', 'F', '0', '46', '实际借款人借款合同公证书', 'T', '实际借款人借款合同公证书', '0', 'sjgzs', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant11', 'F', '0', '50', '他项权证', 'T', '他项权证', '0', 'txqz', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant12', 'F', '0', '54', '土地使用证', 'T', '土地使用证', '0', 'tdsy', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant2', 'F', '0', '6', '查册凭证', 'T', '查册凭证', '0', 'ccpz', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant3', 'F', '0', '16', '抵押物委托公证书', 'T', '抵押物委托公证书', '0', 'dyww', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant4', 'F', '0', '21', '房产证', 'T', '房产证', '0', 'fcz', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant5', 'F', '0', '33', '借款人或买方征信', 'T', '借款人或买方征信', '0', 'jkrz', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant6', 'F', '0', '40', '卖方征信', 'T', '卖方征信', '0', 'mfzx', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant7', 'F', '0', '41', '评估报告或初评意见', 'T', '评估报告或初评意见', '0', 'pgbg', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant8', 'F', '0', '43', '入押回执(抵我司)', 'T', '入押回执(抵我司)', '0', 'ryhz1', 'F', 'F', 'maclass4');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_warrant9', 'F', '0', '44', '入押回执(抵银行)', 'T', '入押回执(抵银行)', '0', 'ryhz2', 'F', 'F', 'maclass4');
/*银行信息类*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('maclass5', 'F', '0', 'maclass5', '银行信息类', '银行信息类', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank1', 'F', '0', '97', '结清凭证', 'T', '结清凭证', '0', 'jqpz', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank10', 'F', '0', '104', '客户还款卡', 'T', '客户还款卡', '0', 'khhkk', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank2', 'F', '0', '48', '赎楼扣款凭证', 'T', '赎楼扣款凭证', '0', 'slkk', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank3', 'F', '0', '67', '赎楼账户卡折', 'T', '赎楼账户卡折', '0', 'slzh', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank4', 'F', '0', '65', '银行对账单', 'T', '银行对账单', '0', 'yhdz', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank5', 'F', '0', '49', '银行放款账户凭证', 'T', '银行放款账户凭证', '0', 'yhzh', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank6', 'F', '0', '68', '银行同贷凭证', 'T', '银行同贷凭证', '0', 'yhtd', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank7', 'F', '0', '73', '资金流水', 'T', '资金流水', '0', 'zjls', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank8', 'F', '0', '102', '自制同贷凭证', 'T', '自制同贷凭证', '0', 'zztd', 'F', 'F', 'maclass5');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_bank9', 'F', '0', '103', '客户收款卡', 'T', '客户收款卡', '0', 'khskk', 'F', 'F', 'maclass5');
/*其他*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('maclass6', 'F', '0', 'maclass6', '其他', '其他', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass_other1', 'F', '0', '42', '其他', 'T', '其他', '0', 'qt', 'F', 'F', 'maclass6');
/*婚况*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('marital', 'F', '0', 'x030300', '婚况', '婚况', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303001', 'F', '0', 'x0303001', '未婚', 'T', '未婚', '0', 'x0303001', 'F', 'F', 'marital');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303002', 'F', '0', 'x0303002', '已婚', 'T', '已婚', '0', 'x0303002', 'F', 'F', 'marital');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303003', 'F', '0', 'x0303003', '初婚', 'T', '初婚', '0', 'x0303003', 'F', 'F', 'marital');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303004', 'F', '0', 'x0303004', '再婚', 'T', '再婚', '0', 'x0303004', 'F', 'F', 'marital');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303005', 'F', '0', 'x0303005', '复婚', 'T', '复婚', '0', 'x0303005', 'F', 'F', 'marital');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303006', 'F', '0', 'x0303006', '丧偶', 'T', '丧偶', '0', 'x0303006', 'F', 'F', 'marital');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303007', 'F', '0', 'x0303007', '离婚', 'T', '离婚', '0', 'x0303007', 'F', 'F', 'marital');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'x0303008', 'F', '0', 'x0303008', '未说明的婚姻状况', 'T', '未说明的婚姻状况', '0', 'x0303008', 'F', 'F', 'marital');
/*配偶职业类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('MaritalcareerType', 'F', '0', 'g050500', '配偶职业类型', '配偶职业类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505001', 'F', '0', 'g0505001', '国家机关、党群组织、企业、事业单位负责人', 'T', '国家机关、党群组织、企业、事业单位负责人', '0', 'g0505001', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505002', 'F', '0', 'g0505002', '专业技术人员', 'T', '专业技术人员', '0', 'g0505002', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505003', 'F', '0', 'g0505003', '办事人员和有关人员', 'T', '办事人员和有关人员', '0', 'g0505003', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505004', 'F', '0', 'g0505004', '商业、服务业人员', 'T', '商业、服务业人员', '0', 'g0505004', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505005', 'F', '0', 'g0505005', '农、林、牧、渔、水利业生产人员', 'T', '农、林、牧、渔、水利业生产人员', '0', 'g0505005', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505006', 'F', '0', 'g0505006', '生产、运输设备操作人员及有关人员', 'T', '生产、运输设备操作人员及有关人员', '0', 'g0505006', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505007', 'F', '0', 'g0505007', '军人', 'T', '军人', '0', 'g0505007', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505008', 'F', '0', 'g0505008', '不便分类的其他从业人员', 'T', '不便分类的其他从业人员', '0', 'g0505008', 'F', 'F', 'MaritalcareerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'g0505009', 'F', '0', 'g0505009', '未知', 'T', '未知', '0', 'g0505009', 'F', 'F', 'MaritalcareerType');
/*资料大类*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('materiaClass', 'F', '0', 'materiaClass', '资料大类', '资料大类', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass1', 'F', '0', 'maclass001', '客户资料', 'T', '客户资料', '0', 'maclass001', 'F', 'F', 'materiaClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass2', 'F', '0', 'maclass002', '合同类', 'T', '合同类', '0', 'maclass002', 'F', 'F', 'materiaClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass3', 'F', '0', 'maclass003', '内审文件夹', 'T', '内审文件夹', '0', 'maclass003', 'F', 'F', 'materiaClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass4', 'F', '0', 'maclass004', '权证类', 'T', '权证类', '0', 'maclass004', 'F', 'F', 'materiaClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass5', 'F', '0', 'maclass005', '银行信息类', 'T', '银行信息类', '0', 'maclass005', 'F', 'F', 'materiaClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'maclass6', 'F', '0', 'maclass006', '其他', 'T', '其他', '0', 'maclass006', 'F', 'F', 'materiaClass');
/*抵押顺位*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('mortgageOrder', 'F', '0', 'mortgageOrder', '抵押顺位', '抵押顺位', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'mortgageOrder01', 'F', '0', 'mortgageOrder01', '抵1', 'T', '抵1', '0', 'mortgageOrder01', 'F', 'F', 'mortgageOrder');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'mortgageOrder02', 'F', '0', 'mortgageOrder02', '抵2', 'T', '抵2', '0', 'mortgageOrder02', 'F', 'F', 'mortgageOrder');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'mortgageOrder03', 'F', '0', 'mortgageOrder03', '抵3', 'T', '抵3', '0', 'mortgageOrder03', 'F', 'F', 'mortgageOrder');
/*类别*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('mt001000', 'F', '0', 'mt001000', '类别', '类别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '委贷银行', 'F', '0', '委贷银行', '委贷银行', 'T', '委贷银行', '0', '委贷银行', 'F', 'F', 'mt001000');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '抵押权人', 'F', '0', '抵押权人', '抵押权人', 'T', '抵押权人', '0', '抵押权人', 'F', 'F', 'mt001000');
/*审批类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('opprovalOpinionType', 'F', '0', 'opprovalOpinionType', '审批类型', '审批类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'opprovalOpinionType01', 'F', '0', 'opprovalOpinionType01', '展期1', 'T', '展期1', '0', 'opprovalOpinionType01', 'F', 'F', 'opprovalOpinionType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'opprovalOpinionType02', 'F', '0', 'opprovalOpinionType02', '展期2', 'T', '展期2', '0', 'opprovalOpinionType02', 'F', 'F', 'opprovalOpinionType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'opprovalOpinionType03', 'F', '0', 'opprovalOpinionType03', '展期3', 'T', '展期3', '0', 'opprovalOpinionType03', 'F', 'F', 'opprovalOpinionType');

/*支付（佣）条件*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('payConditionClass', 'F', '0', 'payConditionClass', '支付（佣）条件', '支付（佣）条件', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'payConditionClass01', 'F', '0', 'payConditionClass01', '赎楼回款', 'T', '不限制', '0', 'payConditionClass01', 'F', 'F', 'payConditionClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'payConditionClass02', 'F', '0', 'payConditionClass02', '赎楼资金', 'T', '本案件收完', '0', 'payConditionClass02', 'F', 'F', 'payConditionClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'payConditionClass03', 'F', '0', 'payConditionClass03', '押金费用', 'T', '本类别收完', '0', 'payConditionClass03', 'F', 'F', 'payConditionClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'payConditionClass04', 'F', '0', 'payConditionClass04', '快贷资金', 'T', '本费用收完', '0', 'payConditionClass04', 'F', 'F', 'payConditionClass');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'payConditionClass05', 'F', '0', 'payConditionClass05', '银行付费', 'T', '组内收完', '0', 'payConditionClass05', 'F', 'F', 'payConditionClass');
/*联系类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('pglxlx', 'F', '0', 'pglxlx', '联系类型', '联系类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'pglxlx001', 'F', '0', 'pglxlx001', '联系类型', 'T', '联系类型', '0', 'pglxlx001', 'F', 'F', 'pglxlx');
/*企业类别*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('pgqylb', 'F', '0', 'pgqylb', '企业类别', '企业类别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'pgqylb001', 'F', '0', 'pgqylb001', '企业类别', 'T', '企业类别', '0', 'pgqylb001', 'F', 'F', 'pgqylb');

/*职务*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('position', 'F', '0', 'p0126', '职务', '职务', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'p01261', 'F', '0', 'p01261', '高管', 'T', '高管', '0', 'p01261', 'F', 'F', 'position');
/*代码*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('processFormCd', 'F', '0', 'processFormCd', '代码', '代码', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'processFormCd01', 'F', '0', 'processFormCd01', '营销申请', 'T', '营销申请', '0', 'processFormCd01', 'F', 'F', 'processFormCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'processFormCd02', 'F', '0', 'processFormCd02', '项目申请', 'T', '项目申请', '0', 'processFormCd02', 'F', 'F', 'processFormCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'processFormCd03', 'F', '0', 'processFormCd03', '展期', 'T', '展期', '0', 'processFormCd03', 'F', 'F', 'processFormCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'processFormCd04', 'F', '0', 'processFormCd04', '面签', 'T', '面签', '0', 'processFormCd04', 'F', 'F', 'processFormCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'processFormCd05', 'F', '0', 'processFormCd05', '放款申请', 'T', '放款申请', '0', 'processFormCd05', 'F', 'F', 'processFormCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'processFormCd06', 'F', '0', 'processFormCd06', '还款计划', 'T', '还款计划', '0', 'processFormCd06', 'F', 'F', 'processFormCd');

/*参数值*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('questionParamCd', 'F', '0', 'questionParamCd', '参数值', '参数值', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'a', 'F', '0', 'a', '0', 'T', '0', '0', 'a', 'F', 'F', 'questionParamCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'b', 'F', '0', 'b', '1', 'T', '1', '0', 'b', 'F', 'F', 'questionParamCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'c', 'F', '0', 'c', '2', 'T', '2', '0', 'c', 'F', 'F', 'questionParamCd');
/*类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('questionSceneCd', 'F', '0', 'questionSceneCd', '类型', '类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'questionSceneCd01', 'F', '0', 'questionSceneCd01', '0', 'T', '面签', '0', 'questionSceneCd01', 'F', 'F', 'questionSceneCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'questionSceneCd02', 'F', '0', 'questionSceneCd02', '1', 'T', '资调', '0', 'questionSceneCd02', 'F', 'F', 'questionSceneCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'questionSceneCd03', 'F', '0', 'questionSceneCd03', '2', 'T', '营销', '0', 'questionSceneCd03', 'F', 'F', 'questionSceneCd');
/*结论类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('questionTypeCd', 'F', '0', 'questionTypeCd', '结论类型', '结论类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'questionTypeCd01', 'F', '0', 'questionTypeCd01', '录入框', 'T', '录入框', '0', 'questionTypeCd01', 'F', 'F', 'questionTypeCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'questionTypeCd02', 'F', '0', 'questionTypeCd02', '单选按钮', 'T', '单选按钮', '0', 'questionTypeCd02', 'F', 'F', 'questionTypeCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'questionTypeCd03', 'F', '0', 'questionTypeCd03', '复选框', 'T', '复选框', '0', 'questionTypeCd03', 'F', 'F', 'questionTypeCd');
/*利率单位%*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('rateUtil', 'F', '0', 'rateUtil', '利率单位%', '利率单位%', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'rateUtilDay', 'F', '0', 'rateUtilDay', '‰/日', 'T', '‰/日', '99', 'rateUtilDate', 'F', 'F', 'rateUtil');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'rateUtilMonth', 'F', '0', 'rateUtilMonth', '%/月', 'T', '%/月', '99', 'rateUtilMonth', 'F', 'F', 'rateUtil');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'rateUtilYear', 'F', '0', 'rateUtilYear', '%/年', 'T', '%/年', '90', 'rateUtilYear', 'F', 'F', 'rateUtil');
/*原因*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('reasonType', 'F', '0', 'r0118', '原因', '原因', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'r01181', 'F', '0', 'r01181', '欺诈', 'T', '欺诈', '0', 'r01181', 'F', 'F', 'reasonType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'r01182', 'F', '0', 'r01182', '逾期', 'T', '逾期', '0', 'r01182', 'F', 'F', 'reasonType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'r01183', 'F', '0', 'r01183', '核销', 'T', '核销', '0', 'r01183', 'F', 'F', 'reasonType');
/*收款方*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('receiverCd', 'F', '0', 'receiverCd', '收款方', '收款方', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'receiverCd01', 'F', '0', 'receiverCd01', '新县银行', 'T', '新县银行', '0', 'receiverCd01', 'F', 'F', 'receiverCd');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'receiverCd02', 'F', '0', 'receiverCd02', '外贸信托', 'T', '外贸信托', '0', 'receiverCd02', 'F', 'F', 'receiverCd');
/*与主借人关系*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('relationship', 'F', '0', 'r0143', '与主借人关系', '与主借人关系', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'r01431', 'F', '0', 'r01431', '朋友', 'T', '朋友', '0', 'r01431', 'F', 'F', 'relationship');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'r01432', 'F', '0', 'r01432', '亲戚', 'T', '亲戚', '0', 'r01432', 'F', 'F', 'relationship');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'r01433', 'F', '0', 'r01433', '同事', 'T', '同事', '0', 'r01433', 'F', 'F', 'relationship');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'r01434', 'F', '0', 'r01434', '配偶', 'T', '配偶', '0', 'r01434', 'F', 'F', 'relationship');
/*手机短信*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('sjdx', 'F', '0', 'sjdx', '手机短信', '手机短信', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'sjdx001', 'F', '0', 'sjdx001', '手机短信', 'T', '手机短信', '0', 'sjdx001', 'F', 'F', 'sjdx');
/*涉及业务*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('sjyw', 'F', '0', 'sjyw', '涉及业务', '涉及业务', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'syjw001', 'F', '0', '1', '一手楼贷款', 'T', '一手楼贷款', '0', '1', 'F', 'F', 'sjyw');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'syjw002', 'F', '0', '2', '二手楼贷款', 'T', '二手楼贷款', '0', '2', 'F', 'F', 'sjyw');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'syjw003', 'F', '0', '3', '个人消费性贷款', 'T', '个人消费性贷款', '0', '3', 'F', 'F', 'sjyw');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'syjw004', 'F', '0', '4', '个人经营性贷款', 'T', '个人经营性贷款', '0', '4', 'F', 'F', 'sjyw');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'syjw005', 'F', '0', '5', '汽车贷款', 'T', '汽车贷款', '0', '5', 'F', 'F', 'sjyw');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'syjw006', 'F', '0', '6', '其他', 'T', '其他', '0', '6', 'F', 'F', 'sjyw');
/*强和弱*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('sors', 'F', '0', 'sors', '强和弱', '强和弱', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'sors001', 'F', '0', '1', '强', 'T', '强', '0', '1', 'F', 'F', 'sors');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'sors002', 'F', '0', '2', '弱', 'T', '弱', '0', '2', 'F', 'F', 'sors');
/*来源*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('s0140', 'F', '0', 's0140', '来源', '来源', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 's01401', 'F', '0', 's01401', '外贸信托', 'T', '外贸信托', '0', 's01401', 'F', 'F', 's0140');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 's01402', 'F', '0', 's01402', '渤海信托', 'T', '渤海信托', '0', 's01402', 'F', 'F', 's0140');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 's01403', 'F', '0', 's01403', '泛华', 'T', '泛华', '0', 's01403', 'F', 'F', 's0140');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 's01404', 'F', '0', 's01404', '第三方', 'T', '第三方', '0', 's01404', 'F', 'F', 's0140');
/*类别*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('lt001000', 'F', '0', 'lt001000', '类别', '类别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '信托贷款合同', 'F', '0', '信托贷款合同', '信托贷款合同', 'T', '信托贷款合同', '0', '信托贷款合同', 'F', 'F', 'lt001000');
/*类别值*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('slvt001000', 'F', '0', 'slvt001000', '类别值', '类别值', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'SQL函数取值', 'F', '0', 'SQL函数取值', 'SQL函数取值', 'T', 'SQL函数取值', '0', 'SQL函数取值', 'F', 'F', 'slvt001000');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'SQL语句取值', 'F', '0', 'SQL语句取值', 'SQL语句取值', 'T', 'SQL语句取值', '0', 'SQL语句取值', 'F', 'F', 'slvt001000');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, '程序函数取值', 'F', '0', '程序函数取值', '程序函数取值', 'T', '程序函数取值', '0', '程序函数取值', 'F', 'F', 'slvt001000');

/*分段还款时间*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('timeType', 'F', '0', 'timeType', '分段还款时间', '分段还款时间', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'timeType01', 'F', '0', 'timeTypeOne', '第一年', 'T', '第一年', '99', 'timeTypeOne', 'F', 'F', 'timeType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'timeType02', 'F', '0', 'timeTypeTwo', '第二年', 'T', '第二年', '99', 'timeTypeTwo', 'F', 'F', 'timeType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'timeType03', 'F', '0', 'timeTypeThree', '第三年', 'T', '第三年', '99', 'timeTypeThree', 'F', 'F', 'timeType');
/*类别*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('type', 'F', '0', 't00100', '类别', '类别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 't001001', 'F', '0', 't001001', '银行', 'T', '银行', '0', 't001001', 'F', 'F', 'type');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 't001002', 'F', '0', 't001002', '往来单位', 'T', '往来单位', '0', 't001002', 'F', 'F', 'type');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 't001003', 'F', '0', 't001003', '中介', 'T', '中介', '0', 't001003', 'F', 'F', 'type');
/*单位性质*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('workUnitNature', 'F', '0', 'w0121', '单位性质', '单位性质', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01211', 'F', '0', 'w01211', '国有企业', 'T', '国有企业', '0', 'w01211', 'F', 'F', 'workUnitNature');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01212', 'F', '0', 'w01212', '国有控股企业', 'T', '国有控股企业', '0', 'w01212', 'F', 'F', 'workUnitNature');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01213', 'F', '0', 'w01213', '外资企业', 'T', '外资企业', '0', 'w01213', 'F', 'F', 'workUnitNature');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01214', 'F', '0', 'w01214', '合资企业', 'T', '合资企业', '0', 'w01214', 'F', 'F', 'workUnitNature');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01215', 'F', '0', 'w01215', '私营企业', 'T', '私营企业', '0', 'w01215', 'F', 'F', 'workUnitNature');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01216', 'F', '0', 'w01216', '事业企业', 'T', '事业企业', '0', 'w01216', 'F', 'F', 'workUnitNature');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01217', 'F', '0', 'w01217', '国家行政机关', 'T', '国家行政机关', '0', 'w01217', 'F', 'F', 'workUnitNature');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'w01218', 'F', '0', 'w01218', '政府', 'T', '政府', '0', 'w01218', 'F', 'F', 'workUnitNature');
/*银行类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('yhlx', 'F', '0', 'yhlx', '银行类型', '银行类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'yhlx001', 'F', '0', 'yhlx001', '银行类型', 'T', '银行类型', '0', 'yhlx001', 'F', 'F', 'yhlx');
/*是和否*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('yorn', 'F', '0', 'yorn', '是和否', '是和否', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'yorn001', 'F', '0', '1', '是', 'T', '是', '0', '1', 'F', 'F', 'yorn');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'yorn002', 'F', '0', '2', '否', 'T', '否', '0', '2', 'F', 'F', 'yorn');
/*终端归属*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('zdgs', 'F', '0', 'zdgs', '终端归属', '终端归属', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zdgs001', 'F', '0', '1', '终端归属', 'T', '终端归属', '0', '1', 'F', 'F', 'zdgs');
/*终端类别*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('zdlb', 'F', '0', 'zdlb', '终端类别', '终端类别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zdlb001', 'F', '0', '1', '终端类别', 'T', '终端类别', '0', '1', 'F', 'F', 'zdlb');
/*终端状态*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('zdzt', 'F', '0', 'zdzt', '终端状态', '终端状态', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zdzt001', 'F', '0', '1', '终端状态', 'T', '终端状态', '0', '1', 'F', 'F', 'zdzt');
/*资方类型*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('zflx', 'F', '0', 'zflx', '资方类型', '资方类型', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zflx001', 'F', '0', 'zflx001', '银行', 'T', '银行', '0', 'zflx001', 'F', 'F', 'zflx');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zflx002', 'F', '0', 'zflx002', 'P2P', 'T', 'P2P', '0', 'zflx002', 'F', 'F', 'zflx');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zflx003', 'F', '0', 'zflx003', '担保', 'T', '担保', '0', 'zflx003', 'F', 'F', 'zflx');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zflx004', 'F', '0', 'zflx004', '小贷', 'T', '小贷', '0', 'zflx004', 'F', 'F', 'zflx');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zflx005', 'F', '0', 'zflx005', '信托', 'T', '信托', '0', 'zflx005', 'F', 'F', 'zflx');
/*终端列表状态*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('zhuangtai', 'F', '0', 'zhuangtai', '终端列表状态', '终端列表状态', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zhuangtai001', 'F', '0', 'zhuangtai001', '启用', 'T', '启用', '0', 'zhuangtai001', 'F', 'F', 'zhuangtai');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'zhuangtai002', 'F', '0', 'zhuangtai002', '停用', 'T', '停用', '0', 'zhuangtai002', 'F', 'F', 'zhuangtai');

/*业务设置-参数管理-抵押权人管理类别下拉liaoguowei*/
INSERT INTO ess_codecategory (id, isDeleted, version, categoryCd, categoryNm, description, isCommon, isEnabled, isTree) VALUES ('mortOwnerType', 'F', '0', 'mortOwnerType', '抵押权人管理类别', '抵押权人管理类别', 'T', 'T', 'F');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'mortOwnerType001', 'F', '0', 'mortOwnerType001', '委贷银行', 'T', '委贷银行', '0', 'mortOwnerType001', 'F', 'F', 'mortOwnerType');
INSERT INTO ess_simplecode (isTree, id, isDeleted, version, code, desc_, isEnable, name, dispOrder, fullCode, isDefault, isFixed, codeCategory_id) VALUES (0, 'mortOwnerType002', 'F', '0', 'mortOwnerType002', '抵押权人', 'T', '抵押权人', '0', 'mortOwnerType002', 'F', 'F', 'mortOwnerType');

















