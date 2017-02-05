/**
 * 产品初始化sql
 */
INSERT INTO prct_category (id, createBy, createOrgCd, createTime, deleteTime, isDeleted, updateBy, updateOrgCd, updateTime, version,orderNumber, parent_id, name) VALUES ('0', NULL, NULL, NULL, NULL, 'F', NULL, NULL, NULL, NULL, '0', NULL, '产品');
/**
 * 特批事项初始化sql
 */
INSERT INTO zf_except_matter (id, createBy, createOrgCd, createTime, deleteTime, isDeleted, updateBy, updateOrgCd, updateTime, version,type, code, name,remark,status,empName) VALUES ('0', NULL, NULL, NOW(), NULL, 'F', '0', NULL, null, NULL, '风险措施', 'TP0000000000001', '其他',null,1,'系统管理员');


/*提前还款规则sql*/
INSERT INTO zf_prepay_rule (id, createBy, createOrgCd, createTime, deleteTime, isDeleted, updateBy, updateOrgCd, updateTime, version,code, name,remark) VALUES ('0', NULL, NULL, NOW(), NULL, 'F', '0', NULL, null, NULL,  'HK001', '提前还款规则1','
 <p><span>提前还款违约金=应补交利息损失差额+提前还款手续费，其中：</span></p><p><span>1、应补交利息损失差额：按照“实际贷款期限对应收费标准×实际贷款期限”测算应收总费用，应补交利息损失差额=应收总费用-已收费用。</span></p><p><span>2、提前还款手续费：（1）贷款期限＜12个月的，无须支付提前还款手续费。（2）贷款期限=12个月的， 6个月（含）内提前还款的，需支付提前还款金额的3%作为提前还款手续费； 7-12个月（含）内提前还款的，需支付提前还款金额的1%作为提前还款手续费。</span></p>
');
INSERT INTO zf_prepay_rule (id, createBy, createOrgCd, createTime, deleteTime, isDeleted, updateBy, updateOrgCd, updateTime, version,code, name,remark) VALUES ('1', NULL, NULL, NOW(), NULL, 'F', '0', NULL, null, NULL,  'HK002', '提前还款规则2','

<p><span>提前还款违约金=应补交利息损失差额+提前还款手续费，其中：</span></p><p><span>1、应补交利息损失差额：针对“经营贷”产品，主借人提还无须缴纳“应补交利息损失差额”。 </span></p><p><span>2、提前还款手续费：在借款6个月（含）内提前还款的，需支付提前还款本金的5%作为提前还款违约金；在借款7-12个月（含）内提前还款的，需支付提前还款金额的3%作为提前还款违约金；在借款12个月后提前还款，需支付提前还款金额的2%作为提前还款违约金；</span></p><p><span>备注：客户提前还款用于续贷，且新贷款本金≥原贷款余额，免收提还违约金。</span></p>
');

INSERT INTO zf_prepay_rule (id, createBy, createOrgCd, createTime, deleteTime, isDeleted, updateBy, updateOrgCd, updateTime, version,code, name,remark) VALUES ('2', NULL, NULL, NOW(), NULL, 'F', '0', NULL, null, NULL,  'HK003', '提前还款规则3','
 <p><span>1、先息后本期间提还不收取提还违约金；</span></p><p><span>2、等额本息还款期间提还的：需支付提前还款金额的2%作为提还手续费。</span></p>
');

