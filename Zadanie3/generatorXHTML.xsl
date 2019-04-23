<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
xmlns="http://www.w3.org/1999/xhtml"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
	<xsl:output method="xhtml" version="1.0" encoding="utf-8" />

	<xsl:template match="/">
		<html>
			<head>
				<title>
					<xsl:value-of select="//informations/name"/>
				</title>
			</head>
			<body style="text-align: center; background-color: #baecff;">
				<xsl:call-template name="informations"/>
				<p/>
				<p/>
				<xsl:call-template name="books"/>
				<p/>
				<p/>
				<xsl:call-template name="authors"/>
				<p/>
				<p/>
				<xsl:call-template name="statistic"/> 
			</body>
		</html>
	</xsl:template>

	<xsl:template name="informations">
		<p style=" font-size: 32px; text-align: center;">
			<b>
				<xsl:value-of select="//informations/description"/>
			</b>
			<p/>
			<p/>
			Authors:
			<p/>
			<p/>
			<xsl:for-each select="//informations/author">
				<xsl:value-of select="."/>
				<p/>
				<p/>
			</xsl:for-each>
			Modyfication date: 
			<xsl:value-of select="//informations/generated"/>
		</p>
	</xsl:template>

	<xsl:template name="books">
		<table border="2" style="text-align: center; margin: 0px auto; border-collapse: collapse; padding: 15px; background-color: #edc390;">
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Authors</th>
				<th>Language</th>
				<th>Binding</th>
				<th>Dimensions</th>
				<th>Price</th>
				<th>For Adult</th>
			</tr>
			<xsl:for-each select="//books/book">
				<tr>
					<td>
						<xsl:value-of select="ISBN"/>
					</td>
					<td>
						<xsl:value-of select="title"/>
					</td>
					<td>
						<xsl:for-each select="authors/author">
							<xsl:value-of select="." separator=" "/>
							<p/>
							<p/>
						</xsl:for-each>
					</td>
					<td>
						<xsl:for-each select="language">
							<xsl:value-of select="." separator=" "/>
							<p/>
							<p/>
						</xsl:for-each>
					</td>
					<td>
						<xsl:value-of select="binding"/>
					</td>
					<td>
						<xsl:value-of select="dimensions"/>
					</td>
					<td>
						<xsl:value-of select="price"/>
					</td>
					<td>
						<xsl:value-of select="if_for_adult"/>
					</td>
				</tr>	
			</xsl:for-each>
		</table>
	</xsl:template>

	<xsl:template name="authors">
		<table border="1" style="text-align: center; margin: 0px auto; border-collapse: collapse; padding: 15px; background-color: #edd4b6;">
			<tr>
				<th>Authors</th>
			</tr>
			<xsl:for-each select="//book_authors_list/book_author">
				<tr>
					<td>
					<!--
						<b>
							<xsl:value-of select="name"/>
							<xsl:value-of select="surname"/>
						</b>
						<xsl:value-of select="author_nation"/>
						-->
						<xsl:value-of select="."/>
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>

	<xsl:template name="statistic">
		<table style="text-align: center; margin: 0px auto; border-collapse: collapse;">
			<tr>
				<td>
					<xsl:value-of select="concat('Position count: ',//statistic/position_count)"/>
				</td>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="concat('Average value: ',//statistic/avg_bookshelf_value)"/>
				</td>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="concat('Number of items in Polish: ',//statistic/number_polish_items)"/>
				</td>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="concat('Number of items in English: ',//statistic/number_eng_items)"/>
				</td>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="concat('Number of items only for adults: ',//statistic/number_book_for_adult)"/>
				</td>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="
				concat('The oldest book: ',//statistic/oldest_book/title, concat(': ',//statistic/oldest_book/realise_day))
				"/>
				</td>
			</tr>
			<tr>
				<td>
					<xsl:value-of select="
				concat('The youngest book: ',//statistic/youngest_book/title, concat(': ',//statistic/youngest_book/realise_day))
				"/>
				</td>
			</tr>
		</table>
	</xsl:template>

</xsl:stylesheet>
