IF exist .\src\main\java\config.properties (
	IF exist .\target\ (
		IF exist .\target\classes\config.properties (
			DEL /F /Q .\target\classes\config.properties
			echo REMOVE
		)
		COPY .\src\main\java\config.properties .\target\classes\config.properties
		echo COPY
	) else (
		echo "Asegúrate de convertir tu proyecto a un proyecto tipo Maven y generar la carpeta target\"
	)
) else (
	echo "Configura primero tu archivo config.properties"
)
pause