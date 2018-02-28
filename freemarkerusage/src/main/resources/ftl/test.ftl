${user}<br>
${latestProduct.url}<br>
${latestProduct.name}

<#assign protect = true>
${protect?string("yes","no")}

${mayNotExist!"default value"}

<#assign x="something">

${indexOf("met",x)}
${indexOf("foo",x)}

<#if testBoolean??>
testBoolean.
</#if>


<#if tradeOrderVo.compensatableForAlbum??>

赔付

</#if>

foo
<@upper>
bar
<#-- All kind of FTL is allowed here -->
    <#list ["red", "green", "blue"] as color>
    ${color}
    </#list>
baaz
</@upper>
wombat

