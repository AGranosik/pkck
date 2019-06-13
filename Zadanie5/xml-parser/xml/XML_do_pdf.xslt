<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:msxsl="urn:schemas-microsoft-com:xslt"
                exclude-result-prefixes="msxsl">
	<xsl:output method="xml"
                encoding="utf-8"/>

	<xsl:template match="/">
		<fo:root font-family="sans-serif">

			<fo:layout-master-set>
				<fo:simple-page-master master-name="Summary" page-height="297mm" page-width="210mm" margin-top="5mm" margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
					<fo:region-body margin-top="10mm" margin-bottom="10mm"/>
					<fo:region-after region-name="xsl-region-after" extent="10mm"/>
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="Summary">

				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<xsl:apply-templates select="/subsidiary/informations/generated"/>
					</fo:block>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">

					<fo:block font-weight="bold" text-align="center" font-size="large">
						<xsl:text>Statistics:</xsl:text>
					</fo:block>
					<xsl:apply-templates select="/subsidiary/statistic"/>

					<fo:block font-weight="bold" text-align="center" font-size="large" margin-top="10mm">
						<xsl:text>Books:</xsl:text>
					</fo:block>
					<fo:table margin-left="2mm" margin-right="2mm" margin-bottom="10mm" margin-top="5mm" table-layout="fixed" width="196mm" font-size="8px">
						<fo:table-header>
							<fo:table-row>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">ISBN</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">Title</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">Language</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">Authors</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">Binding</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">Dimensions</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">Price</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="left">
									<fo:block font-weight="bold">For adult</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-header>
						<fo:table-body>
							<xsl:apply-templates select="/subsidiary/books"/>
						</fo:table-body>
					</fo:table>

				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template match="/subsidiary/statistic">
		<fo:list-block>
			<fo:list-item>
				<fo:list-item-label>
					<fo:block>
                            Position count:
					</fo:block>
				</fo:list-item-label>
				<fo:list-item-body>
					<fo:block font-style="italic" margin-left="15em">
						<xsl:value-of select="position_count" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
			<fo:list-item>
				<fo:list-item-label>
					<fo:block>
                            Average bookshelf value:
					</fo:block>
				</fo:list-item-label>
				<fo:list-item-body>
					<fo:block font-style="italic" margin-left="15em">
						<xsl:value-of select="avg_bookshelf_value" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
			<fo:list-item>
				<fo:list-item-label>
					<fo:block>
                            Number of polish items:
					</fo:block>
				</fo:list-item-label>
				<fo:list-item-body>
					<fo:block font-style="italic" margin-left="15em">
						<xsl:value-of select="number_polish_items" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
			<fo:list-item>
				<fo:list-item-label>
					<fo:block>
                            Number of english items:
					</fo:block>
				</fo:list-item-label>
				<fo:list-item-body>
					<fo:block font-style="italic" margin-left="15em">
						<xsl:value-of select="number_eng_items" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
			<fo:list-item>
				<fo:list-item-label>
					<fo:block>
                            Number books for adult:
					</fo:block>
				</fo:list-item-label>
				<fo:list-item-body>
					<fo:block font-style="italic" margin-left="15em">
						<xsl:value-of select="number_book_for_adult" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
			<fo:list-item>
				<fo:list-item-label>
					<fo:block>
                            Oldest book:
					</fo:block>
				</fo:list-item-label>
				<fo:list-item-body>
					<fo:block font-style="italic" margin-left="15em">
						<xsl:value-of select="oldest_book/title" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
			<fo:list-item>
				<fo:list-item-label>
					<fo:block>
                            Youngest book:
					</fo:block>
				</fo:list-item-label>
				<fo:list-item-body>
					<fo:block font-style="italic" margin-left="15em">
						<xsl:value-of select="youngest_book/title" />
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
		</fo:list-block>
	</xsl:template>

	<xsl:template match="/subsidiary/books">
		<xsl:for-each select="book">
			<fo:table-row line-height="20mm" top="2mm" bottom="2mm" left="2mm" right="2mm" border-color="black" border-width="0.6mm" border-style="solid">
				<fo:table-cell>
					<fo:block line-height="5mm" font-style="italic">
						<xsl:value-of select="ISBN" />
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block line-height="5mm">
						<xsl:value-of select="title"/>
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block line-height="5mm">
						<xsl:for-each select="language">
							<xsl:value-of select="concat(.,'&#x9;')" />
						</xsl:for-each>
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block line-height="5mm">
						<xsl:for-each select="author">
							<xsl:value-of select="concat(name,' ')" />
							<xsl:value-of select="concat(surname,'&#xA;')" />
						</xsl:for-each>
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block line-height="5mm">
						<xsl:value-of select="binding" />
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block line-height="5mm">
						<xsl:value-of select="dimensions" />
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block line-height="5mm">
						<xsl:value-of select="dimensions" />
					</fo:block>
				</fo:table-cell>
				<fo:table-cell>
					<fo:block line-height="5mm">
						<xsl:value-of select="if_for_adult" />
					</fo:block>
				</fo:table-cell>
			</fo:table-row>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="/subsidiary/informations/generated">
		<fo:block text-align="right">
			<xsl:value-of select="concat('Generated: ',.)" />
		</fo:block>
	</xsl:template>

</xsl:stylesheet>