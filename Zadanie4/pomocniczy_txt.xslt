<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text"
                encoding="utf-8"
                indent="yes"/>

	<xsl:variable name="spaces" select="'                                        '"/>
	<xsl:template match="/">
		<xsl:text>Statistic:&#xA;&#xA;</xsl:text>
		<xsl:apply-templates select="/subsidiary/statistic"/>
		<xsl:text>----------------------------------------------------&#xA;&#xA;</xsl:text>
		<xsl:text>Books:&#xA;&#xA;</xsl:text>
		<xsl:apply-templates select="/subsidiary/books/book"/>
		<xsl:text>----------------------------------------------------&#xA;&#xA;</xsl:text>
		<xsl:text>List of book authors:&#xA;&#xA;</xsl:text>
		<xsl:apply-templates select="/subsidiary/book_authors_list/book_author"/>
	</xsl:template>

	<xsl:template match="/subsidiary/statistic">
		<xsl:text>Position_count:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Position_count:'))" />
		<xsl:value-of select="concat(position_count,'&#xA;')" />
		<xsl:text>Average bookshelf value:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Average bookshelf value:'))" />
		<xsl:value-of select="concat(avg_bookshelf_value,'&#xA;')" />
		<xsl:text>Number of polish items:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Number of polish items:'))" />
		<xsl:value-of select="concat(number_polish_items,'&#xA;')" />
		<xsl:text>Number of english items:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Number of english items:'))" />
		<xsl:value-of select="concat(number_eng_items,'&#xA;')" />
		<xsl:text>Number books for adult:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Number books for adult:'))" />
		<xsl:value-of select="concat(number_book_for_adult,'&#xA;')" />
		<xsl:text>Oldest book:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Oldest_book:'))" />
		<xsl:value-of select="concat(oldest_book/title,'&#x9;')" />
		<xsl:value-of select="concat(oldest_book/realise_day,'&#xA;')" />
		<xsl:text>Youngest book:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Youngest_book:'))" />
		<xsl:value-of select="concat(youngest_book/title,'&#x9;')" />
		<xsl:value-of select="concat(youngest_book/realise_day,'&#xA;')" />
		<xsl:text>&#xA;</xsl:text>
	</xsl:template>

	<xsl:template match="/subsidiary/books/book">
		<xsl:text>ISBN:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('ISBN:'))" />
		<xsl:value-of select="concat(ISBN,'&#xA;')" />
		<xsl:text>Title:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Title:'))" />
		<xsl:value-of select="concat(title,'&#xA;')" />
		<xsl:text>Language:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Language:'))" />
		<xsl:for-each select="language">
			<xsl:value-of select="concat(.,'&#x9;')" />
		</xsl:for-each>
		<xsl:text>&#xA;</xsl:text>
		<xsl:text>Authors:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Authors:'))" />
		<xsl:for-each select="authors/author">
			<xsl:value-of select="concat(name,' ')" />
			<xsl:value-of select="concat(surname,'&#x9;')" />
		</xsl:for-each>
		<xsl:text>&#xA;</xsl:text>
		<xsl:text>Binding:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Binding:'))" />
		<xsl:value-of select="concat(binding,'&#xA;')" />
		<xsl:text>Dimensions:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Dimensions:'))" />
		<xsl:value-of select="concat(dimensions,'&#xA;')" />
		<xsl:text>Price:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Price:'))" />
		<xsl:value-of select="concat(price,'&#xA;')" />
		<xsl:text>For adults:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('For adults:'))" />
		<xsl:value-of select="concat(if_for_adult,'&#xA;')" />
		<xsl:text>&#xA;</xsl:text>
	</xsl:template>

	<xsl:template match="/subsidiary/book_authors_list/book_author">
		<xsl:text>Name:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Name:'))" />
		<xsl:value-of select="concat(name,'&#xA;')" />
		<xsl:text>Surname:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Surname:'))" />
		<xsl:value-of select="concat(surname,'&#xA;')" />
		<xsl:text>Nation:</xsl:text>
		<xsl:value-of select="substring($spaces, 0, 28 - string-length('Nation:'))" />
		<xsl:value-of select="concat(author_nation,'&#xA;')" />
		<xsl:value-of select="concat(if_for_adult,'&#xA;')" />
	</xsl:template>

</xsl:stylesheet>