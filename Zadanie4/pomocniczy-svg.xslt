<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.w3.org/2000/svg">
	<xsl:output method="xml"
                media-type="image/svg"/>

	<xsl:template match="/">
		<svg xmlns="http://www.w3.org/2000/svg" width="1500" height="500" >

			<script type="text/ecmascript"> 
				<![CDATA[
                    function onClickBar(evt, source) 
                    {
                        var round = Math.round, rand = Math.random, n = 255;
                        var color = 'rgba(' + round(rand()*n) + ',' + round(rand()*n) + ',' + round(rand()*n) + ',' + rand().toFixed(1) + ')';
                        source.setAttribute("fill", color);
                    }
					function onMouseOverText(evt) 
                    {
                        var text = document.getElementsByClassName("informationAuthors")[0];
						var informations = document.getElementsByClassName("details")[0];
                        text.setAttribute("visibility", "visible");
						informations.setAttribute("fill", "blue");
                    }
					function onMouseOutText(evt) 
                    {
                        var text = document.getElementsByClassName("informationAuthors")[0];
						var informations = document.getElementsByClassName("details")[0];
                        text.setAttribute("visibility", "hidden");
						informations.setAttribute("fill", "black");
                    }
            ]]>
			</script>      

			<rect x="0" y="0" width="700" height="400" fill="rgb(122, 125, 128)"/>
			<text x="250" y="30" font-size="24" fill="white" font-weight="bold" font-family="sans-serif">
                Books amount
			</text>
			<g id="poleWykresu">
				<rect x="50" y="50" width="600" height="300" fill="rgb(228, 254, 255)" stroke="DimGrey"/>
				<xsl:apply-templates select="/subsidiary/statistic"/>
			</g>
		</svg>
	</xsl:template>

	<xsl:template match="/subsidiary/statistic">
		<xsl:variable name="barWidth" select="100"/>
		<xsl:variable name="posX" select="150"/>
		<xsl:variable name="posYTtile" select="300"/>
		<xsl:variable name="multiplier" select="5"/>


		<text x="{$posX}" y="{$posYTtile}" font-size="16" fill="black" font-weight="bold" font-family="sans-serif">
            Position count
		</text>
		<text visibility="visible" x="{$posX + 20}" y="{270 - position_count*$multiplier}" font-size="16" fill="black" font-weight="bold" font-family="sans-serif">
			<xsl:value-of select="position_count"/>
		</text>
		<rect x="{$posX}" width="{$barWidth}" fill="black" stroke="black" onclick="onClickBar(evt, this)">
			<animate attributeName="height" from="0" to="{position_count*$multiplier}" dur="1s" fill="freeze"/>
			<animate attributeName="y" from="280" to="{280 - position_count*$multiplier}" dur="1s" fill="freeze"/>
		</rect>


		<text x="{$posX + 150}" y="{$posYTtile}" font-size="16" fill="black" font-weight="bold" font-family="sans-serif">
            Polish positions
		</text>
		<text visibility="visible" x="{$posX + 20 + 150}" y="{270 - number_polish_items*$multiplier}" font-size="16" fill="black" font-weight="bold" font-family="sans-serif">
			<xsl:value-of select="number_polish_items"/>
		</text>
		<rect x="{$posX + 150}" width="{$barWidth}" fill="black" stroke="black" onclick="onClickBar(evt, this)">
			<animate attributeName="height" from="0" to="{number_polish_items*$multiplier}" dur="1s" fill="freeze"/>
			<animate attributeName="y" from="280" to="{280 - number_polish_items*$multiplier}" dur="1s" fill="freeze"/>
		</rect>

		<text x="{$posX + 300}" y="{$posYTtile}" font-size="16" fill="black" font-weight="bold" font-family="sans-serif">
            English positions
		</text>
		<text visibility="visible" x="{$posX + 20 + 300}" y="{270 - number_eng_items*$multiplier}" font-size="16" fill="black" font-weight="bold" font-family="sans-serif">
			<xsl:value-of select="number_eng_items"/>
		</text>
		<rect x="{$posX + 300}" width="{$barWidth}" fill="black" stroke="black" onclick="onClickBar(evt, this)">
			<animate attributeName="height" from="0" to="{number_eng_items*$multiplier}" dur="1s" fill="freeze"/>
			<animate attributeName="y" from="280" to="{280 - number_eng_items*$multiplier}" dur="1s" fill="freeze"/>
		</rect>

		<text class="details" x="800" y="50" font-size="16" fill="black" font-weight="bold" font-family="sans-serif" onmouseover="onMouseOverText(evt)" onmouseout="onMouseOutText(evt)">
			Show details
		</text>
		<text class="informationAuthors" x="800" y="100" visibility="hidden" font-size="16" fill="black" font-weight="bold" font-family="sans-serif" >
			Authors:
			<xsl:for-each select="/subsidiary/informations/author">
				<tspan x="800" dy="1.4em">
					<xsl:value-of select="." />
				</tspan>
			</xsl:for-each>
		</text>

	</xsl:template>

</xsl:stylesheet>