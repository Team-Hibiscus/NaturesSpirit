from pathlib import Path

colors = ["Maroon","Peach","Vermilion","Amber","Banana","Mold","Artichoke","Sage","Shamrock","Sap","Mint","Cerulean","Navy","Periwinkle","Indigo","Grape","Fuchsia","Velvet","Mauve","Acorn",]

json_files = Path(".").glob("input/advancements/**/*abcd*.json")

for json in json_files:
  for color in colors:
    new_path = Path(f"{json}".replace("abcd", color.lower()).replace("input", "output"))
    new_path.parent.mkdir(parents=True, exist_ok=True)
    file = open(new_path, "x")
    file.write(json.read_text().replace("abcd", color.lower()))
    file.close()