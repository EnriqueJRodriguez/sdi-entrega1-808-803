$(document).ready(function() {
	$("#languageDropdownMenuButton a").click(function(e) {
		e.preventDefault(); // cancel the link behaviourvarlanguage
		SelectedText = $(this).text();
		varlanguageSelectedValue = $(this).attr("value");
		$("#btnLanguage").text(languageSelectedText);
		window.location.replace('?lang=' + languageSelectedValue);
		returnfalse;
	});
});}