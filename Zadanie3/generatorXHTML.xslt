<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" 
				xmlns="http://www.w3.org/1999/xhtml"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
	<xsl:output method="xhtml" version="1.0" encoding="utf-8" />

	<xsl:template match="/">
		<html>
			<head>
				<link rel="stylesheet" type="text/css" href="bookshelf.css" />
				<title>
					<xsl:value-of select="//informations/name"/>
				</title>
			</head>
			<body id="main_style">
				<xsl:call-template name="informations"/>
				<xsl:call-template name="books"/>
				<p/>
				<xsl:call-template name="authors"/>
				<p/>
				<xsl:call-template name="statistic"/>
			</body>
		</html>
	</xsl:template>

	<xsl:template name="informations">
		<p>
			<b>
				<xsl:value-of select="//informations/description"/>
			</b>
		</p>
		<p>Authors:</p>
		<xsl:for-each select="//informations/author">
			<p>
				<xsl:value-of select="."/>
			</p>
		</xsl:for-each>
		<p>
			Modyfication date: 
			<xsl:value-of select="//informations/generated"/>
		</p>
	</xsl:template>

	<xsl:template name="books">
		<table id="books_table">
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
							<p>
								<xsl:value-of select="." separator=" "/>
							</p>
						</xsl:for-each>
					</td>
					<td>
						<xsl:for-each select="language">
							<p>
								<xsl:value-of select="." separator=" "/>
							</p>

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
		<table id="authors_table">
			<tr>
				<th>Authors</th>
			</tr>
			<xsl:for-each select="//book_authors_list/book_author">
				<tr>
					<td>
						<xsl:value-of select="."/>
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>

	<xsl:template name="statistic">
		<table id="statistic_table">
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
